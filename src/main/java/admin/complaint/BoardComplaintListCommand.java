package admin.complaint;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminDAO;
import admin.AdminInterface;
import admin.board.BoardComplaintVO;

public class BoardComplaintListCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		
		ArrayList<BoardComplaintVO> vos = dao.boardComplaintList();
		
		request.setAttribute("vos", vos);
		request.setAttribute("complaintCnt", vos.size());
	}

}
