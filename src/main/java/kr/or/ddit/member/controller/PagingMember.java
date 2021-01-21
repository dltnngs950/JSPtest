package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.page.PageVo;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVo;

@WebServlet("/pagingMember.do")
public class PagingMember extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PagingMember.class);
	IMemberService memberService = new MemberService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		logger.debug("paging doGet() -----------------");
		String pageParam = req.getParameter("page");
		String sizeParam = req.getParameter("pageSize");
		
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		int pageSize = sizeParam == null ? 5 : Integer.parseInt(sizeParam);
		
		logger.debug("page : ", pageParam);
		logger.debug("pageSize : ", sizeParam);
		
		// pageVo
		PageVo pageVo = new PageVo(page, pageSize);
		// 페이징 리스트
		Map<String, Object> map = memberService.getPagingMember(pageVo);
				
		List<MemberVo> paginglist = (List<MemberVo>)map.get("pagingList");
		int memCnt = (int)map.get("memCnt");
		int pagination = (int)Math.ceil((double) memCnt / pageSize); 
		
		req.setAttribute("pageVo", pageVo);
		
		req.setAttribute("paginglist", paginglist);
		req.setAttribute("pagination", pagination);
		
		req.getRequestDispatcher("/member/memberlist.jsp").forward(req, resp);
				
		
	}
	
	

}
