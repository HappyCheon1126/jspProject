package study.j1101;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j1101/ex5_jstlOk")
public class Ex5_JstlOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Jstl4VO> vos = new ArrayList<>();
		
		Jstl4VO vo = new Jstl4VO();
		vo.setName("홍길동");
		vo.setAge(25);
		vo.setGender("남자");
		vo.setJob("학생");
		vo.setAddress("서울");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("김말숙");
		vo.setAge(22);
		vo.setGender("여자");
		vo.setJob("회사원");
		vo.setAddress("청주");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("이기자");
		vo.setAge(35);
		vo.setGender("남자");
		vo.setJob("공무원");
		vo.setAddress("제주");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("오하늘");
		vo.setAge(19);
		vo.setGender("여자");
		vo.setJob("학생");
		vo.setAddress("청주");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("고인돌");
		vo.setAge(55);
		vo.setGender("남자");
		vo.setJob("자영업");
		vo.setAddress("서울");
		vos.add(vo);
		
		vo = new Jstl4VO();
		vo.setName("고은별");
		vo.setAge(29);
		vo.setGender("여자");
		vo.setJob("연예인");
		vo.setAddress("부산");
		vos.add(vo);
		
		// 앞쪽으로는 자료 저장처리하는 부분, 뒤쪽으로는 넘어온 매개변수에 따른 선별작업처리
		String flag = request.getParameter("flag")==null ? "" : request.getParameter("flag");
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		String gender = request.getParameter("gender")==null ? "" : request.getParameter("gender");
		
		ArrayList<Jstl4VO> vosCheck = new ArrayList<>();
		
		if(flag.equals("name")) {
			for(Jstl4VO v : vos) {
				if(v.getName().equals(name)) {
					vosCheck.add(v);
				}
			}
			request.setAttribute("vos", vosCheck);
			request.setAttribute("title", name + "님 정보");
		}
		else if(flag.equals("gender")) {
			for(Jstl4VO v : vos) {
				if(v.getGender().equals(gender)) {
					vosCheck.add(v);
				}
			}
			request.setAttribute("vos", vosCheck);
			request.setAttribute("title", gender + " 정보 리스트");
		}
		else {
			request.setAttribute("vos", vos);
			request.setAttribute("title", "전체 자료 리스트");
		}
		
		
		String viewPage = "/study/1101_JSTL/ex5_jstl.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
