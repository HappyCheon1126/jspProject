package webMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.review.ReviewVO;
import common.GetConn;
import pds.PdsVO;

public class WebMessageDAO {
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	WebMessageVO vo = null;
	
	// pstmt 객체 반납
	public void pstmtClose() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
	}
	
	// rs 객체 반납
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
			finally {
				pstmtClose();
			}
		}
	}

	// 메세지 리스트(전체메세지(신규/읽은메세지)/새메세지/보낸메세지/휴지통)
	public ArrayList<WebMessageVO> getMessageList(String mid, int mSw, int startIndexNo, int pageSize) {
		ArrayList<WebMessageVO> vos = new ArrayList<WebMessageVO>();
		try {
			if(mSw == 1) {	// 받은 메세지(전체메세지(새메세지+읽은메세지))
				sql = "select *, timestampdiff(hour,sendDate, now()) as hour_diff from webMessage where receiveId=? and (receiveSw='n' or receiveSw='r') order by idx desc limit ?,?";
			}
			else if(mSw == 2)	{ // 새메세지
				sql = "select *, timestampdiff(hour,sendDate, now()) as hour_diff from webMessage where receiveId=? and receiveSw='n' order by idx desc limit ?,?";
			}
			else if(mSw == 3)	{ // 보낸 메세지
				sql = "select *, timestampdiff(hour,sendDate, now()) as hour_diff from webMessage where sendId=? and sendSw='s' order by idx desc limit ?,?";
			}
			else if(mSw == 4)	{ // 수신확인
				sql = "select *, timestampdiff(hour,sendDate, now()) as hour_diff from webMessage where sendId=? and receiveSw='n' order by idx desc limit ?,?";
			}
			else if(mSw == 5)	{ // 휴지통
				sql = "select *, timestampdiff(hour,sendDate, now()) as hour_diff from webMessage where (receiveId=? and receiveSw='g') or (sendId=? and sendSw='g') order by idx desc limit ?,?";
			}
			else {	// mSw 가 0일때는 새로운 메세지 작성처리이다.
				return vos;
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			if(mSw == 5) {
				pstmt.setString(2, mid);
				pstmt.setInt(3, startIndexNo);
				pstmt.setInt(4, pageSize);
			}
			else {
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new WebMessageVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setSendId(rs.getString("sendId"));
				vo.setSendSw(rs.getString("sendSw"));
				vo.setSendDate(rs.getString("sendDate"));
				vo.setReceiveId(rs.getString("receiveId"));
				vo.setReceiveSw(rs.getString("receiveSw"));
				vo.setReceiveDate(rs.getString("receiveDate"));
				
				vo.setHour_diff(rs.getShort("hour_diff"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("sql 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 웹 메세지 작성처리
	public int setWmInputOk(WebMessageVO vo) {
		int res = 0;
		try {
			sql = "insert into webMessage values (default,?,?,?,'s',default,?,'n',default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getSendId());
			pstmt.setString(4, vo.getReceiveId());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 상세내역보기
	public WebMessageVO getWmMessageContent(int idx, int mFlag) {
		vo = new WebMessageVO();
		try {
			if(mFlag != 15) {
				sql = "update webMessage set receiveSw='r', receiveDate=now() where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.executeUpdate();
				pstmtClose();
			}
			sql = "select * from webMessage where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			rs.next();
			
			vo = new WebMessageVO();
			vo.setIdx(rs.getInt("idx"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setSendId(rs.getString("sendId"));
			vo.setSendSw(rs.getString("sendSw"));
			vo.setSendDate(rs.getString("sendDate"));
			vo.setReceiveId(rs.getString("receiveId"));
			vo.setReceiveSw(rs.getString("receiveSw"));
			vo.setReceiveDate(rs.getString("receiveDate"));
		} catch (SQLException e) {
			System.out.println("sql 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 휴지통으로 이동처리
	public int wmDeleteCheck(int idx, int mSw) {
		int res = 0;
		try {
			if(mSw == 11) {
				sql = "update webMessage set receiveSw = 'g' where idx = ?";
			}
			else {
				sql = "update webMessage set sendSw = 'x' where idx = ?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 휴지통에 들어있는 모든 자료들을 삭제처리한다.(이때 receiveSw와 sendSw가 모두 'x'이면 실제로 자료를 삭제처리한다.)
	public int wmDeleteAll(String mid) {
		int res = 0;
		try {
			sql = "update webMessage set receiveSw = 'x' where receiveId = ? and receiveSw = 'g'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "update webMessage set sendSw = 'x' where sendId = ? and sendSw = 'g'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "delete from webMessage where sendSw = 'x' and receiveSw = 'x'";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			res = 1;
		} catch (SQLException e) {
			System.out.println("sql 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 메세지 건수 구하기
	public int getTotRecCnt(String mid, int mSw) {
		int totRecCnt = 0;
		try {
			if(mSw == 1) {	// 받은 메세지(전체메세지(새메세지+읽은메세지))
				sql = "select count(*) from webMessage where receiveId=? and (receiveSw='n' or receiveSw='r')";
			}
			else if(mSw == 2)	{ // 새메세지
				sql = "select count(*) from webMessage where receiveId=? and receiveSw='n'";
			}
			else if(mSw == 3)	{ // 보낸 메세지
				sql = "select count(*) from webMessage where sendId=? and sendSw='s'";
			}
			else if(mSw == 4)	{ // 수신확인
				sql = "select count(*) from webMessage where sendId=? and receiveSw='n'";
			}
			else if(mSw == 5)	{ // 휴지통
				sql = "select count(*) from webMessage where (receiveId=? and receiveSw='g') or (sendId=? and sendSw='g')";
			}
			else {	// mSw 가 0일때는 새로운 메세지 작성처리이다.
				return totRecCnt;
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			if(mSw == 5) {
				pstmt.setString(2, mid);
			}
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("sql 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecCnt;
	}

}
