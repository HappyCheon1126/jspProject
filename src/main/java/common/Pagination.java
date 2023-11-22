package common;

import javax.servlet.http.HttpServletRequest;

public class Pagination {
	
	public static void pageChange(HttpServletRequest request, int pag, int pageSize, int totRecCnt, int startIndexNo, String part, String search, String searchString) {
		// 페이징처리 추가로 진행한다.
		int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1 ;
		int curScrStartNo = totRecCnt - startIndexNo;
		
	  int blockSize = 3;
		int curBlock = (pag - 1) / blockSize;
		int lastBlock = (totPage - 1) / blockSize;

		// 작업을 마친후 view에서 처리할 변수들(받은변수와 처리한 변수들)을 모두 request저장소에 담아준다.(view에서 사용하지 않는 totRecCnt와 startIndexNo는 제외)
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totPage", totPage);
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		
		request.setAttribute("part", part);
		request.setAttribute("search", search);
		request.setAttribute("searchString", searchString);
	}
}
