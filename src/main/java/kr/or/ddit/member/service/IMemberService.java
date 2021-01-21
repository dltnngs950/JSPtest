package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.page.PageVo;
import kr.or.ddit.member.vo.MemberVo;

public interface IMemberService {

	// 한명의 멤버 정보 가져오기
	MemberVo getMember(String userid); 
	
	// 모든 멤버 가져오기
	List<MemberVo> getAllMember();
	
	// 페이징 멤버 가져오기
	Map<String, Object> getPagingMember(PageVo vo);
	
	// 모든 멤버 수 가져오기
	int getAllMemCnt();
	

}
