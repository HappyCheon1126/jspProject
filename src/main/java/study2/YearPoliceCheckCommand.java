package study2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.apiTest.ApiDAO;
import study2.apiTest.CrimeVO;

public class YearPoliceCheckCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = request.getParameter("year")==null ? 0 : Integer.parseInt(request.getParameter("year"));
		String police = request.getParameter("police")==null ? "" : request.getParameter("police");
		String yearOrder_ = request.getParameter("yearOrder")==null ? "" : request.getParameter("yearOrder");
		
		String yearOrder = "";
		if(yearOrder_.equals("d")) yearOrder = "order by police desc";
		else yearOrder = "";
		
		ApiDAO dao = new ApiDAO();
		
		ArrayList<CrimeVO> vos = dao.getYearPoliceCheck(year,police,yearOrder);
		
		CrimeVO analyzeVo = dao.getAnalyzeTotal(year, police, yearOrder);
		
		request.setAttribute("vos", vos);
		request.setAttribute("year", year);
		request.setAttribute("analyzeVo", analyzeVo);
		request.setAttribute("police", police);
		request.setAttribute("yearOrder", yearOrder_);
	}

}
