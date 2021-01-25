package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.page.ConditionVo;
import kr.or.ddit.common.page.PageVo;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVo;

@MultipartConfig
@WebServlet("/pagingMember.do")
public class PagingMember extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PagingMember.class);
	IMemberService memberService = new MemberService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		doPost(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.debug("paging doPost() -----------------");
		String pageParam = req.getParameter("page");
		String pageSizeParam = req.getParameter("pageSize");
		String type = req.getParameter("type");
		String contents = req.getParameter("search");
		String con = "%"+contents+"%";
		
		
		logger.debug("pageParam : {} ", pageParam);
		logger.debug("pageSizeParam : {}", pageSizeParam);
		
		int page = 1;
		
		if(pageParam == null){
			page = 1;
		}else if(pageParam.equals("")) {
			page = 1;
		}else {
			page = Integer.parseInt(pageParam);
		}

		int pageSize = 5;
		
		if(pageSizeParam == null || pageSizeParam.equals("")) {
			pageSize = 5;
		}else {
			pageSize = Integer.parseInt(pageSizeParam);
		}
		
		logger.debug("-----------------------------");
		logger.debug("page : {}", page);
		logger.debug("pageSize {}", pageSize);
		logger.debug("type : {}", type);
		logger.debug("contents : {}", contents);
		
		Map<String, Object> map = new HashMap<>();
		
		// pageVo
		PageVo pageVo = new PageVo(page, pageSize);
	
		if(type == null || contents == null || type.equals("") || contents.equals("")) {
			map = memberService.getPagingMember(pageVo);
		}else if(type.equals("id")) {
			if(con.equals("%%")) {
				map = memberService.getPagingMember(pageVo);
			}else {
				ConditionVo conVo = new ConditionVo(con, page, pageSize);
				map = memberService.getConditionMemberbyid(conVo);	
			}
		}else if(type.equals("nm")) {
			if(con.equals("%%")) {
				map = memberService.getPagingMember(pageVo);
			}else {
				ConditionVo conVo = new ConditionVo(con, page, pageSize);
				map = memberService.getConditionMemberbynm(conVo);	
			}
		}else if(type.equals("al")) {
			if(con.equals("%%")) {
				map = memberService.getPagingMember(pageVo);
			}else {
				ConditionVo conVo = new ConditionVo(con, page, pageSize);
				map = memberService.getConditionMemberbyali(conVo);	
			}
		}

				
		List<MemberVo> paginglist = (List<MemberVo>)map.get("pagingList");
		int memCnt = (int)map.get("memCnt");
		
		logger.debug("Cnt : {}", memCnt);
		
		int pagination = (int)Math.ceil((double) memCnt / pageSize); 
		
		logger.debug("pagination : {}", pagination);
		
		if(pageSizeParam.equals("") || pageSizeParam == null) {
			String d= "";
			req.setAttribute("pageSize", d);
		}else {
			req.setAttribute("pageSize", pageSize);
		}
		
		
		logger.debug("page : {}", pageSize);
		
		
		
		req.setAttribute("contents", contents);
		req.setAttribute("type", type);
		req.setAttribute("pageVo", pageVo);
		req.setAttribute("paginglist", paginglist);
		req.setAttribute("pagination", pagination);
		
		req.getRequestDispatcher("/member/memberlist.jsp").forward(req, resp);
	}
	
	
	

}
