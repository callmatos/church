package com.church.model.service.member;

import org.church.core.service.GenericService;

import com.church.model.entity.Member;
import com.church.model.repository.member.MemberRepository;


public interface MemberService extends GenericService<MemberResult, MemberRepository, Member>{

	public MemberResult createMember(MemberResult memberResult) throws Exception;
	
	public MemberResult createMembers(MemberResult memberResult);
	
}
