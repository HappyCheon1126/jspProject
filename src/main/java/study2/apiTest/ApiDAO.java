package study2.apiTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.board.BoardComplaintVO;
import admin.review.ReviewVO;
import common.GetConn;

public class ApiDAO {
	private Connection conn = GetConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	CrimeVO vo = null;
	
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

	public void setCrimeSaveOk(CrimeVO vo) {
		try {
			sql = "insert into crime values (default,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getYear());
			pstmt.setString(2, vo.getPolice());
			pstmt.setInt(3, vo.getRobbery());
			pstmt.setInt(4, vo.getMurder());
			pstmt.setInt(5, vo.getTheft());
			pstmt.setInt(6, vo.getViolence());
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	// 범죄 년도 검색
	public int getSearchYear(int year) {
		int res = 0;
		try {
			sql = "select * from crime where year = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			if(rs.next()) res = 1;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return res;
	}

	public String setCrimeDeleteOk(int year) {
		String str = "삭제 실패~~";
		try {
			sql = "delete from crime where year = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			pstmt.executeUpdate();
			str = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return str;
	}

	// 년도별 전체 자료 검색
	public ArrayList<CrimeVO> getListYear(int year) {
		ArrayList<CrimeVO> vos = new ArrayList<CrimeVO>();
		try {
			sql = "select * from crime where year = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CrimeVO vo = new CrimeVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setYear(rs.getInt("year"));
				vo.setPolice(rs.getString("police"));
				vo.setRobbery(rs.getInt("robbery"));
				vo.setMurder(rs.getInt("murder"));
				vo.setTheft(rs.getInt("theft"));
				vo.setViolence(rs.getInt("vioLence"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류.. : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 자료 분석(총점/평균) 해서 출력하기
	public CrimeVO getAnalyze(int year) {
		CrimeVO analyzeVo = new CrimeVO();
		try {
			sql = "select year,sum(robbery) as totRobbery,sum(murder) as totMurder,sum(theft) as totTheft,sum(violence) as totViolence, "
					+ "avg(robbery) as avgRobbery,avg(murder) as avgMurder,avg(theft) as avgTheft,avg(violence) as avgViolence "
					+ "from crime where year = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				analyzeVo.setYear(year);
				analyzeVo.setTotRobbery(rs.getInt("totRobbery"));
				analyzeVo.setTotMurder(rs.getInt("totMurder"));
				analyzeVo.setTotTheft(rs.getInt("totTheft"));
				analyzeVo.setTotViolence(rs.getInt("totViolence"));
				analyzeVo.setAvgRobbery(rs.getInt("avgRobbery"));
				analyzeVo.setAvgMurder(rs.getInt("avgMurder"));
				analyzeVo.setAvgTheft(rs.getInt("avgTheft"));
				analyzeVo.setAvgViolence(rs.getInt("avgViolence"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return analyzeVo;
	}

	// 경찰서 이름 검색처리
	public ArrayList<CrimeVO> getPoliceCheck(String police) {
		ArrayList<CrimeVO> vos = new ArrayList<CrimeVO>();
		try {
			sql = "select * from crime where police like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+police+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CrimeVO vo = new CrimeVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setYear(rs.getInt("year"));
				vo.setPolice(rs.getString("police"));
				vo.setRobbery(rs.getInt("robbery"));
				vo.setMurder(rs.getInt("murder"));
				vo.setTheft(rs.getInt("theft"));
				vo.setViolence(rs.getInt("vioLence"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 경찰서별 통계처리
	public CrimeVO getAnalyzePolice(String police) {
		CrimeVO analyzeVo = new CrimeVO();
		try {
			sql = "select year,sum(robbery) as totRobbery,sum(murder) as totMurder,sum(theft) as totTheft,sum(violence) as totViolence, "
					+ "avg(robbery) as avgRobbery,avg(murder) as avgMurder,avg(theft) as avgTheft,avg(violence) as avgViolence "
					+ "from crime where police like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+police+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				analyzeVo.setYear(rs.getInt("year"));
				analyzeVo.setTotRobbery(rs.getInt("totRobbery"));
				analyzeVo.setTotMurder(rs.getInt("totMurder"));
				analyzeVo.setTotTheft(rs.getInt("totTheft"));
				analyzeVo.setTotViolence(rs.getInt("totViolence"));
				analyzeVo.setAvgRobbery(rs.getInt("avgRobbery"));
				analyzeVo.setAvgMurder(rs.getInt("avgMurder"));
				analyzeVo.setAvgTheft(rs.getInt("avgTheft"));
				analyzeVo.setAvgViolence(rs.getInt("avgViolence"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return analyzeVo;
	}

	// 통합검색
	public ArrayList<CrimeVO> getYearPoliceCheck(int year, String police, String yearOrder) {
		ArrayList<CrimeVO> vos = new ArrayList<CrimeVO>();
		try {
			sql = "select * from crime where year=? and police like ? " + yearOrder;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			pstmt.setString(2, "%"+police+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CrimeVO vo = new CrimeVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setYear(rs.getInt("year"));
				vo.setPolice(rs.getString("police"));
				vo.setRobbery(rs.getInt("robbery"));
				vo.setMurder(rs.getInt("murder"));
				vo.setTheft(rs.getInt("theft"));
				vo.setViolence(rs.getInt("vioLence"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	public CrimeVO getAnalyzeTotal(int year, String police, String yearOrder) {
		CrimeVO analyzeVo = new CrimeVO();
		try {
			sql = "select year,sum(robbery) as totRobbery,sum(murder) as totMurder,sum(theft) as totTheft,sum(violence) as totViolence, "
					+ "avg(robbery) as avgRobbery,avg(murder) as avgMurder,avg(theft) as avgTheft,avg(violence) as avgViolence "
					+ "from crime where year=? and police like ?" + yearOrder;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, year);
			pstmt.setString(2, "%"+police+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				analyzeVo.setYear(rs.getInt("year"));
				analyzeVo.setTotRobbery(rs.getInt("totRobbery"));
				analyzeVo.setTotMurder(rs.getInt("totMurder"));
				analyzeVo.setTotTheft(rs.getInt("totTheft"));
				analyzeVo.setTotViolence(rs.getInt("totViolence"));
				analyzeVo.setAvgRobbery(rs.getInt("avgRobbery"));
				analyzeVo.setAvgMurder(rs.getInt("avgMurder"));
				analyzeVo.setAvgTheft(rs.getInt("avgTheft"));
				analyzeVo.setAvgViolence(rs.getInt("avgViolence"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return analyzeVo;
	}

	
}
