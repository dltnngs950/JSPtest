package kr.or.ddit.member.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.common.page.PageVo;
import kr.or.ddit.member.repository.IMemberDao;
import kr.or.ddit.member.repository.MemberDao;
import kr.or.ddit.member.vo.MemberVo;

public class MemberService implements IMemberService{
	IMemberDao memberdao = new MemberDao();

	@Override
	public MemberVo getMember(String userid) {
		
			return memberdao.getMember(userid);
	}

	@Override
	public List<MemberVo> getAllMember() {
		
		return memberdao.getAllMember();
	}
	
	@Override
	public int getAllMemCnt() {
		
		return memberdao.getAllMemCnt();
	}
	
	@Override
	public Map<String, Object> getPagingMember(PageVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		
		List<MemberVo> pagingList = memberdao.getPagingMember(vo);
		int memCnt = memberdao.getAllMemCnt();

		map.put("pagingList", pagingList);
		map.put("memCnt", memCnt);
		
		return map;

	}

}
