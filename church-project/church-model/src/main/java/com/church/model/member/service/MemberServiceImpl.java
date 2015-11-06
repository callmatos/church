package com.church.model.member.service;

import java.util.ArrayList;
import java.util.List;

import org.church.core.service.AbsGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.church.model.member.entity.Member;
import com.church.model.member.repository.MemberRepository;

@Service
public class MemberServiceImpl extends AbsGenericService<MemberResult, MemberRepository, Member> implements MemberService{

	@Autowired
	private MemberRepository memberRepository;
	
	// Constructor
	public MemberServiceImpl(){
		super(MemberRepository.class,MemberResult.class);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public MemberResult createMember(MemberResult memberResult)
			throws Exception {
		
		final Member me = memberResult.getMember();
		
		//Here have all validation necessary.
		if(me.getPassword() == null)
			me.setPassword("12345678");
		
		memberResult.setMember(this.memberRepository.save(me));
		
		return memberResult;
	}

	@Override
	public MemberResult createMembers(MemberResult memberResult) {
		
		// verify if has members to save.
		if(CollectionUtils.isEmpty(memberResult.getMembers()))
			return memberResult;
		
		// Variable to add member saved.
		final List<Member> resultMemberSave = new ArrayList<Member>();
		
		// for to save all member.
		for (Member mm : memberResult.getMembers()) {
			MemberResult temp = new MemberResult(mm);
			
			try {
				resultMemberSave.add(this.createMember(temp).getMember());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// clean the last list of member.
		memberResult.getMembers().clear();
		
		//set the new list of member with your Id.
		memberResult.setMembers(resultMemberSave);
		
		return memberResult;
	}

	
	
}
