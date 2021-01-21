package kr.or.ddit.member.repository;

import java.util.List;

import kr.or.ddit.common.page.PageVo;
import kr.or.ddit.member.vo.MemberVo;

public interface IMemberDao {
	
	// 한명의 멤버 정보 가져오기
	MemberVo getMember(String userid); 
	
	// 모든 멤버 가져오기
	List<MemberVo> getAllMember();
	
	// 페이징 멤버 가져오기
	List<MemberVo> getPagingMember(PageVo pagevo);
	
	// 모든 멤버 수 가져오기
	int getAllMemCnt();

}
