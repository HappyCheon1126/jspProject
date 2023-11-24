package schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleDeleteOkCommand implements ScheduleInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		ScheduleDAO dao = new ScheduleDAO();
		
		int res = dao.setScheduleDeleteOk(idx);
		
		response.getWriter().write(res+"");
	}

}
