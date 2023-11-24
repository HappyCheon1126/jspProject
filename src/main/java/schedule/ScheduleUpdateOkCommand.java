package schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScheduleUpdateOkCommand implements ScheduleInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		String part = request.getParameter("part")==null ? "" : request.getParameter("part");
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		
		ScheduleVO vo = new ScheduleVO();
		vo.setIdx(idx);
		vo.setPart(part);
		vo.setContent(content);
		
		ScheduleDAO dao = new ScheduleDAO();
		
		int res = dao.setScheduleUpdateOk(vo);
		
		response.getWriter().write(res+"");
	}

}
