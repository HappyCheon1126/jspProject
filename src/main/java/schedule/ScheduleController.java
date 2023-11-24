package schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.sc")
public class ScheduleController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScheduleInterface command = null;
		String viewPage = "/WEB-INF/schedule";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")==null ? 999 : (int) session.getAttribute("sLevel");
		
		if(level > 4) {
			request.getRequestDispatcher("/").forward(request, response);
		}
		else if(com.equals("/schedule")) {
			command = new ScheduleCommand();
			command.execute(request, response);
			viewPage += "/schedule.jsp";
		}
		else if(com.equals("/scheduleMenu")) {
			command = new ScheduleMenuCommand();
			command.execute(request, response);
			viewPage += "/scheduleMenu.jsp";
		}
		else if(com.equals("/scheduleInputOk")) {
			command = new ScheduleInputOkCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/scheduleDeleteOk")) {
			command = new ScheduleDeleteOkCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/scheduleUpdateOk")) {
			command = new ScheduleUpdateOkCommand();
			command.execute(request, response);
			return;
		}
				
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
