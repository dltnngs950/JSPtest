package kr.or.ddit.member.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.page.ConditionVo;
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

	@Override
	public int registMember(MemberVo memVo) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int registmem = sqlSession.insert("member.registMember", memVo);
		
		if (registmem == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return registmem;
	}

	@Override
	public int modifyMember(MemberVo memVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int modifyCnt = sqlSession.update("member.modifyMember", memVo);
		
		if (modifyCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return modifyCnt;
	}

	@Override
	public int deleteMember(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int deleteCnt = sqlSession.delete("member.deleteMember", userid);
		
		if (deleteCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		
		return deleteCnt;
	}

	@Override
	public List<MemberVo> getConditionMemberbyid(ConditionVo conVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<MemberVo> memvo = sqlSession.selectList("member.getConditionMemberbyid", conVo);
		
		sqlSession.close();
		
		return memvo;
	}

	@Override
	public int getCntMemberbyid(String userid) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int idCnt = sqlSession.selectOne("member.getCntMemberbyid", userid);
		
		sqlSession.close();
		
		return idCnt;
	}

	@Override
	public List<MemberVo> getConditionMemberbyali(ConditionVo conVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<MemberVo> memvo = sqlSession.selectList("member.getConditionMemberbyali", conVo);
		
		sqlSession.close();
		
		return memvo;
	}

	@Override
	public int getCntMemberbyali(String alias) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int alCnt = sqlSession.selectOne("member.getCntMemberbyali", alias);
		
		sqlSession.close();
		
		return alCnt;
	}

	@Override
	public List<MemberVo> getConditionMemberbynm(ConditionVo conVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<MemberVo> memvo = sqlSession.selectList("member.getConditionMemberbynm", conVo);
		
		sqlSession.close();
		
		return memvo;
	}

	@Override
	public int getCntMemberbynm(String usernm) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int nmCnt = sqlSession.selectOne("member.getCntMemberbynm", usernm);
		
		sqlSession.close();
		
		return nmCnt;
	}
	
	

}
