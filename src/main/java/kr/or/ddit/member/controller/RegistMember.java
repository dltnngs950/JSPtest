package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVo;
import kr.or.ddit.util.FileUtil;

@MultipartConfig
@WebServlet("/registMember")
public class RegistMember extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RegistMember.class);

	IMemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		logger.debug("deGet() ----");
		String userid = req.getParameter("userid");
		
		logger.debug("userid : {}", userid);
		
		MemberVo vo = memberService.getMember(userid);
		
		req.setAttribute("vo", vo);
		
		req.getRequestDispatcher("/member/memberRegist.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		logger.debug("doPost(...)");
		
		String memid = req.getParameter("mememid");
		logger.debug("mememid파라미터 : {}", memid);
		
		
		MemberVo mem = memberService.getMember(memid);
		
		String userid = req.getParameter("userid");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		String alias = req.getParameter("alias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");
		
		logger.debug("userid : {}", userid);
		logger.debug("pass : {}", pass);
		logger.debug("name : {}", name);
		logger.debug("alias : {}", alias);
		
		Part profile = req.getPart("profile");
		String filename = "";
		String realfilename = "";
		if (profile.getSize() > 0) {
			filename = FileUtil.getFileName(profile.getHeader("content-Disposition"));
			String fileExtension = FileUtil.getFileExtension(filename);
			// brown // brown.png
			realfilename = UUID.randomUUID().toString() + fileExtension;

			profile.write("d:/upload/" + realfilename);
		}

		MemberVo memVo = new MemberVo(userid, pass, name, alias, addr1, addr2, zipcode, filename, realfilename);
		
		int memberCnt = memberService.registMember(memVo);
		
		if(memberCnt == 1) {
			resp.sendRedirect(req.getContextPath()+"pagingMember.do?page=1&pageSize=5");
//			req.getRequestDispatcher("/pagingMember.do").forward(req, resp);
		}else {
			doGet(req, resp);
		}
		
	}

}
