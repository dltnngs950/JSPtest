package kr.or.ddit.basic.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(BasicServlet.class);

	@Override
	public void init() throws ServletException {
		logger.debug("init...");
	}

}
