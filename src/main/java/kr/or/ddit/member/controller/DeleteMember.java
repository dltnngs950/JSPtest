package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberService;

@WebServlet("/deleteMember")
public class DeleteMember extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DeleteMember.class);

	IMemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("userid");

		int deleteCnt = memberService.deleteMember(userid);
		
		if(deleteCnt == 1) {
			resp.sendRedirect(req.getContextPath() + "pagingMember.do?page=1&pageSize=5");
		}else {
			req.getRequestDispatcher("/member/memberDelete.jsp").forward(req, resp);
		}
	}

}
