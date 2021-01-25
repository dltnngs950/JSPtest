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
@WebServlet("/memberModify")
public class MemberModify extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MemberModify.class);
	IMemberService memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userid = req.getParameter("userid");
		
		MemberVo memVo = memberService.getMember(userid);
		
		req.setAttribute("member", memVo);
		
		req.getRequestDispatcher("/member/memberModify.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.debug("doPost() -------***-------");
		
		req.setCharacterEncoding("UTF-8");
		
		String userid = req.getParameter("userid");
		String usernm = req.getParameter("name");
		String pass = req.getParameter("pass");
		String alias = req.getParameter("alias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");
		
		logger.debug("userid : {}", userid );
		
		logger.debug("usernm : {}", usernm );
		logger.debug("pass : {}", pass );
		logger.debug("alias : {}", alias );
		logger.debug("addr1 : {}", addr1 );
		
		// null값일 경우 생각 해야 할 수도 있음
		MemberVo memvo1 = memberService.getMember(userid);
		String existreal = memvo1.getRealfilename();
		String existfile = memvo1.getFilename();
		
		Part profile = req.getPart("profile");
		String filename = "";
		String realfilename = "";
		if(profile.getSize() > 0) {
			filename = FileUtil.getFileName(profile.getHeader("content-Disposition"));
			String fileExtension = FileUtil.getFileExtension(filename);
			// brown // brown.png
			realfilename = UUID.randomUUID().toString() + fileExtension;
			
			profile.write("d:/upload/" + realfilename);
		}
		
		logger.debug("old filename : " + existfile );		
		logger.debug("old realfilename : " + existreal );
		logger.debug("-----------------------------------" );
		logger.debug("new filename : " + filename);
		logger.debug("new realfilename : " + realfilename);
		
		int memCnt = 0;

		if(realfilename.equals("") && filename.equals("")) {
			MemberVo memVo = new MemberVo();
			memVo.setUsernm(usernm);
			memVo.setPass(pass);
			memVo.setAlias(alias);
			memVo.setAddr1(addr1);
			memVo.setAddr2(addr2);
			memVo.setZipcode(zipcode);
			memVo.setFilename(existfile);
			memVo.setRealfilename(existreal);
			memVo.setUserid(userid);
			memCnt = memberService.modifyMember(memVo);
		}else {
			MemberVo memVo = new MemberVo();
			memVo.setUsernm(usernm);
			memVo.setPass(pass);
			memVo.setAlias(alias);
			memVo.setAddr1(addr1);
			memVo.setAddr2(addr2);
			memVo.setZipcode(zipcode);
			memVo.setFilename(filename);
			memVo.setRealfilename(realfilename);
			memVo.setUserid(userid);
			memCnt = memberService.modifyMember(memVo);
		}
		
		logger.debug("memCnt : {}", memCnt);
		
		if(memCnt == 1) {
			// 사용자 아이디 정보를 가지고가므로 redirect를 하는게 수월하다.
			resp.sendRedirect(req.getContextPath() + "/memberDetail?userid=" + userid);
//			req.getRequestDispatcher("/pagingMember.do").forward(req, resp);
		}else {
			 doGet(req, resp);
		}
		
		
	}

}
