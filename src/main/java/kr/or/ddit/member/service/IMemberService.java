package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.page.ConditionVo;
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
	
	// 멤버 등록
	int registMember(MemberVo memVo);
	
	// 멤버 수정
	int modifyMember(MemberVo memVo);
		
	// 멤버 삭제
	int deleteMember(String userid);
	
	// 아이디 조건 페이징
	Map<String, Object> getConditionMemberbyid(ConditionVo conVo);

	// 별명 조건 페이징
	Map<String, Object> getConditionMemberbyali(ConditionVo conVo);
	
	// 이름 조건 페이징
	Map<String, Object> getConditionMemberbynm(ConditionVo conVo);

	// 아이디 조건 카운트
	int getCntMemberbyid(String userid);
	// 별명조건 카운트
	int getCntMemberbyali(String alias);
	// 이름 조건 카운트
	int getCntMemberbynm(String usernm);
	

}
