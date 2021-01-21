package kr.or.ddit.member.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.page.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.vo.MemberVo;

public class MemberDao implements IMemberDao{
	
	@Override
	public MemberVo getMember(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		MemberVo membervo = sqlSession.selectOne("member.getMember", userid);
		
		sqlSession.close();
		
		return membervo;
	}

	@Override
	public List<MemberVo> getAllMember() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<MemberVo> memberlist = sqlSession.selectList("member.getAllMember");
		
		sqlSession.close();
		
		return memberlist;
	}

	@Override
	public List<MemberVo> getPagingMember(PageVo pagevo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<MemberVo> paginglist = sqlSession.selectList("member.getPagingMember", pagevo);
		
		sqlSession.close();
		
		return paginglist;
	}

	@Override
	public int getAllMemCnt() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int MemCnt = sqlSession.selectOne("member.getAllMemCnt");
		
		sqlSession.close();
		
		return MemCnt;
	}
	
	

}
