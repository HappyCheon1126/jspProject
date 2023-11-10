package study2.mapping2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test5_4Command implements TestReInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int su1 = request.getParameter("su1")==null ? 0 : Integer.parseInt(request.getParameter("su1"));
		int su2 = request.getParameter("su2")==null ? 0 : Integer.parseInt(request.getParameter("su2"));
		String op = request.getParameter("op")==null ? "+" : request.getParameter("op");
		
		int res = 0;
		
		if(op.equals("+")) res = su1 + su2;
		else if(op.equals("*")) res = su1 * su2;
		else if(op.equals("-")) res = su1 - su2;
		
		request.setAttribute("su1", su1);
		request.setAttribute("su2", su2);
		request.setAttribute("op", op);
		request.setAttribute("res", res);
	}

}
