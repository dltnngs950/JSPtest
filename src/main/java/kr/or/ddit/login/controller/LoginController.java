package kr.or.ddit.login.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.page.PageVo;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVo;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	IMemberService memberService = new MemberService();
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String pageParam = req.getParameter("page");
		String sizeParam = req.getParameter("pageSize");
		String memid = req.getParameter("memid");
		
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		int pageSize = sizeParam == null ? 5 : Integer.parseInt(sizeParam);
		
		MemberVo memberVo = memberService.getMember(memid);
		
		logger.debug("memid : {} ", memid);
		
		// pageVo
		PageVo pageVo = new PageVo(page, pageSize);
		// 페이징 리스트
		Map<String, Object> map = memberService.getPagingMember(pageVo);
		
		List<MemberVo> paginglist = (List<MemberVo>)map.get("pagingList");
		int memCnt = (int)map.get("memCnt");
		int pagination = (int)Math.ceil((double) memCnt / pageSize); 
		
		logger.debug("memCnt : {} ", memCnt);
		logger.debug("pagination : {} ", pagination);
		
		req.setAttribute("member", memberVo);
		req.setAttribute("paginglist", paginglist);
		req.setAttribute("pageVo", pageVo);
		req.setAttribute("pagination", pagination);
	
		req.getRequestDispatcher("/member/memberlist.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String userid = req.getParameter("id");
		String pass = req.getParameter("pwd");

		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			logger.debug("cookie.getName() : {} / cookie.getValue() : {} ", cookie.getName(), cookie.getValue());

			if (cookie.getName().equals("id")) {
				Cookie newServerCookie = new Cookie("newServerCookie", "testValue");
				resp.addCookie(newServerCookie);
			}
		}
		
		// 로그인확인
		MemberVo memberVo = memberService.getMember(userid);

		// 아이디가 존재하지 않을 경우
		if (memberVo == null) {
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}

		//아이디를 정확히 입력했을경우 로그인
		if (userid != null && pass.equals(memberVo.getPass())) {
			
			HttpSession session = req.getSession();
			session.setAttribute("S_USER", memberVo.getUserid());

			doGet(req, resp);

		} else {
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		
		
	}

}
