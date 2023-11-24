package study2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.apiTest.ApiDAO;
import study2.apiTest.CrimeVO;

public class ListCrimeDataCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = request.getParameter("year")==null ? 0 : Integer.parseInt(request.getParameter("year"));
		ApiDAO dao = new ApiDAO();
		
		ArrayList<CrimeVO> vos = dao.getListYear(year);
		
		CrimeVO analyzeVo = dao.getAnalyze(year);
		
		request.setAttribute("vos", vos);
		request.setAttribute("year", year);
		request.setAttribute("analyzeVo", analyzeVo);
	}

}
