package study2.ajax3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MemberListCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDAO dao = new LoginDAO();
		
		ArrayList<LoginVO> vos = dao.getLoginList();
		
		JSONObject jObj = null;
		JSONArray jArray = new JSONArray();
		
		HashMap<String, String> map = new HashMap<>();
		
		for(LoginVO vo : vos) {
			map.put("idx",vo.getIdx()+"");
			map.put("mid",vo.getMid());
			map.put("pwd",vo.getPwd());
			map.put("name",vo.getName());
			map.put("point",vo.getPoint()+"");
			map.put("lastDate",vo.getLastDate());
			map.put("todayCount",vo.getTodayCount()+"");
			
			jObj = new JSONObject(map);
			jArray.add(jObj);
		}
		//System.out.println("map : " + map);
		response.getWriter().write(jArray.toString());
	}

}
