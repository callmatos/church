package com.church.model.member.service;

import org.church.core.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.church.model.entity.member.Member;
import com.church.model.member.repository.MemberRepository;


public interface MemberService extends GenericService<MemberResult, MemberRepository, Member>{

	public MemberResult createMember(MemberResult memberResult) throws Exception;
	
	public MemberResult createMembers(MemberResult memberResult);
	
//	public Page<Member> paginationByName(String name, Pageable pageable);
	
}
