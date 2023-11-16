package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.GetConn;

public class BoardDAO {
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	BoardVO vo = null;
	
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

	// 게시판 리스트 처리(전체 조회)
	public ArrayList<BoardVO> getBoardList(int startIndexNo, int pageSize, String search, String searchString) {
		ArrayList<BoardVO> vos = new ArrayList<BoardVO>();
		try {
			if(search.equals("")) {
				sql = "select *,datediff(wDate, now()) as date_diff, timestampdiff(hour,wDate, now()) as hour_diff, "
						+ "(select count(*) from boardReply where boardIdx=b.idx) as replyCnt "
						+ "from board b order by idx desc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startIndexNo);
				pstmt.setInt(2, pageSize);
			}
			else {
				sql = "select *,datediff(wDate, now()) as date_diff, timestampdiff(hour,wDate, now()) as hour_diff, "
						+ "(select count(*) from boardReply where boardIdx=b.idx) as replyCnt "
						+ " from board where "+search+" like ? order by idx desc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchString+"%");
				pstmt.setInt(2, startIndexNo);
				pstmt.setInt(3, pageSize);
			}
			rs = pstmt.executeQuery();

			while(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setwDate(rs.getString("wDate"));
				vo.setGood(rs.getInt("good"));
				
				vo.setHour_diff(rs.getInt("hour_diff"));
				vo.setDate_diff(rs.getInt("date_diff"));
				
				vo.setReplyCnt(rs.getInt("replyCnt"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 게시글 저장하기
	public int setBoardInputOk(BoardVO vo) {
		int res = 0;
		try {
			sql = "insert into board values (default,?,?,?,?,?,?,default,?,?,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getNickName());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getHomePage());
			pstmt.setString(6, vo.getContent());
			pstmt.setString(7, vo.getHostIp());
			pstmt.setString(8, vo.getOpenSw());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 전체 게시글의 레코드건수 구해오기
	public int getTotRecCnt(String search, String searchString) {
		int totRecCnt = 0;
		try {
			if(search.equals("")) {
				sql = "select count(*) as cnt from board";
				pstmt = conn.prepareStatement(sql);
			}
			else {
				sql = "select count(*) as cnt from board where "+search+" like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+searchString+"%");
			}
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return totRecCnt;
	}

	// 게시물 1건 상세보기
	public BoardVO getBoardContent(int idx) {
		vo = new BoardVO();
		try {
			sql = "select * from board where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setwDate(rs.getString("wDate"));
				vo.setGood(rs.getInt("good"));
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 게시글 조회수 1씩 증가시키기
	public void setBoardReadNumPlus(int idx) {
		try {
			sql = "update board set readNum = readNum + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 좋아요수 1씩 증가시키기..(중복불허)
	public void setBoardGoodCheck(int idx) {
		try {
			sql = "update board set good = good + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}
	
	/* 아래 좋아요 +1/-1 증가/감소 처리는 같은 작업의 반복으로 1개의 루틴으로 처리하는것이 좋다.
	// 좋아요수 1씩 증가시키기..(중복허용)
	public void setBoardGoodCheckPlus(int idx) {
		try {
			sql = "update board set good = good + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}
	
	// 좋아요수 1씩 감소시키기..(중복허용)
	public void setBoardGoodCheckMinus(int idx) {
		try {
			sql = "update board set good = good - 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}
	*/
	// 좋아요 '증가/감소' 한번에 처리 루틴
	public void setBoardGoodCheckPlusMinus(int idx, int goodCnt) {
		try {
			sql = "update board set good = good + ? where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goodCnt);
			pstmt.setInt(2, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 게시글 수정처리
	public int setBoardUpdateOk(BoardVO vo) {
		int res = 0;
		try {
			sql = "update board set title=?,email=?,homePage=?,content=?,openSw=?,hostIp=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getHomePage());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getOpenSw());
			pstmt.setString(6, vo.getHostIp());
			pstmt.setInt(7, vo.getIdx());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 게시물 삭제처리...
	public int setBoardDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from board where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 검색기를 활용한 검색자료 추출처리
	public ArrayList<BoardVO> getBoardContentSearch(String search, String searchString) {
		ArrayList<BoardVO> vos = new ArrayList<BoardVO>();
		try {
			sql = "select *,datediff(wDate, now()) as day_diff,timestampdiff(hour,wDate, now()) as hour_diff from board where "+search+" like ? order by idx desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchString+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setHomePage(rs.getString("homePage"));
				vo.setContent(rs.getString("content"));
				vo.setReadNum(rs.getInt("readNum"));
				vo.setHostIp(rs.getString("hostIp"));
				vo.setOpenSw(rs.getString("openSw"));
				vo.setwDate(rs.getString("wDate"));
				vo.setGood(rs.getInt("good"));
				
				vo.setHour_diff(rs.getInt("hour_diff"));
				vo.setDate_diff(rs.getInt("day_diff"));
				
				vos.add(vo);
			}			
			
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	public BoardVO getPreNexSearch(int idx, String str) {
		vo = new BoardVO();
		try {
			if(str.equals("preVo")) {
				sql = "select idx,title from board where idx < ? order by idx desc limit 1";
			}
			else {
				sql = "select idx,title from board where idx > ? order by idx limit 1";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 댓글 읽어오기(원본글 idx에 해당하는 댓글을 읽어온다.)
	public ArrayList<BoardReplyVO> getBoardReply(int idx) {
		ArrayList<BoardReplyVO> replyVos = new ArrayList<BoardReplyVO>();
		try {
			sql = "select * from boardReply where boardIdx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardReplyVO replyVo = new BoardReplyVO();
				replyVo.setIdx(rs.getInt("idx"));
				replyVo.setBoardIdx(rs.getInt("boardIdx"));
				replyVo.setMid(rs.getString("mid"));
				replyVo.setNickName(rs.getString("nickName"));
				replyVo.setwDate(rs.getString("wDate"));
				replyVo.setHostIp(rs.getString("hostIp"));
				replyVo.setContent(rs.getString("content"));
				
				replyVos.add(replyVo);
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return replyVos;
	}

	// 댓글 저장하기
	public int setReplyInputOk(BoardReplyVO replyVo) {
		int res = 0;
		try {
			sql = "insert into boardReply values (default,?,?,?,default,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyVo.getBoardIdx());
			pstmt.setString(2, replyVo.getMid());
			pstmt.setString(3, replyVo.getNickName());
			pstmt.setString(4, replyVo.getHostIp());
			pstmt.setString(5, replyVo.getContent());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 댓글 삭제처리
	public int setBoardReplyDeleteOk(int idx) {
		int res = 0;
		try {
			sql = "delete from boardReply where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL구문 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}
