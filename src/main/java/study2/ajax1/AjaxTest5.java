package study2.ajax1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import study2.login.LoginDAO;
import study2.login.LoginVO;

@SuppressWarnings("serial")
@WebServlet("/ajaxTest5")
public class AjaxTest5 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginSearch(mid);
		
		HashMap<String, String> map = new HashMap<>();
		map.put("mid", vo.getMid());
		map.put("name", vo.getName());
		map.put("point", vo.getPoint()+"");
		map.put("todayCount", vo.getTodayCount()+"");

		//System.out.println("map : " + map);
		
		// JSON형식의 자료로 변경처리한다. 라이브러리 주소 : https://code.google.com/archive/p/json-simple/downloads
		JSONObject jObj = new JSONObject(map);
		//System.out.println("jObj : " + jObj);
		
		// 여러개의 vo객체를 보내고자 할때는 JSON배열로 담아서 처리한다.
		JSONArray jArray = new JSONArray();
		
		jArray.add(jObj);
		
		map = new HashMap<>();
		map.put("mid", "atom1234");
		map.put("name", "아톰");
		map.put("point", "1000");
		map.put("todayCount", "3");
		
		jObj = new JSONObject(map);
		jArray.add(jObj);
		
		System.out.println("jArray : " + jArray);
		
		response.getWriter().write(jArray.toString());
	}
}
