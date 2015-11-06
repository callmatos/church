package com.church.model.member.service;

import org.church.core.service.GenericService;

import com.church.model.member.entity.Member;
import com.church.model.member.repository.MemberRepository;


public interface MemberService extends GenericService<MemberResult, MemberRepository, Member>{

	public MemberResult createMember(MemberResult memberResult) throws Exception;
	
	public MemberResult createMembers(MemberResult memberResult);
	
}
