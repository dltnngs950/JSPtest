package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.common.page.ConditionVo;
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

	@Override
	public int registMember(MemberVo memVo) {
		
		try {
			return memberdao.registMember(memVo);
			
		}catch(Exception e) {
			return 0;
		}
		
	}

	@Override
	public int modifyMember(MemberVo memVo) {
		
		try {
			return memberdao.modifyMember(memVo);
		}catch(Exception e) {
			return 0;
		}
		
	}

	@Override
	public int deleteMember(String userid) {
		try {
			return memberdao.deleteMember(userid);
			
		}catch(Exception e) {
			return 0;
		}
	}

	@Override
	public Map<String, Object> getConditionMemberbyid(ConditionVo conVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int idCnt = 0;
		
		List<MemberVo> pagingList = memberdao.getConditionMemberbyid(conVo);
		
		idCnt = memberdao.getCntMemberbyid(conVo.getContents());

		map.put("pagingList", pagingList);
		map.put("memCnt", idCnt);
		
		return map;
	}

	@Override
	public int getCntMemberbyid(String userid) {
		
		return memberdao.getCntMemberbyid(userid);
	}

	@Override
	public Map<String, Object> getConditionMemberbyali(ConditionVo conVo) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		
		List<MemberVo> pagingList = memberdao.getConditionMemberbyali(conVo);
		int alCnt = memberdao.getCntMemberbyali(conVo.getContents());

		map.put("pagingList", pagingList);
		map.put("memCnt", alCnt);
		
		return map;
	}

	@Override
	public int getCntMemberbyali(String alias) {
		
		return memberdao.getCntMemberbyali(alias);
	}

	@Override
	public Map<String, Object> getConditionMemberbynm(ConditionVo conVo) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		
		List<MemberVo> pagingList = memberdao.getConditionMemberbynm(conVo);
		int nmCnt = memberdao.getCntMemberbynm(conVo.getContents());

		map.put("pagingList", pagingList);
		map.put("memCnt", nmCnt);
		
		return map;
	}

	@Override
	public int getCntMemberbynm(String usernm) {
		
		return memberdao.getCntMemberbynm(usernm);
	}



}
