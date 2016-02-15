package com.church.model.member.service;

import java.util.ArrayList;
import java.util.List;

import org.church.core.service.AbsGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.church.model.entity.member.Member;
import com.church.model.member.repository.MemberRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl extends AbsGenericService<MemberResult, MemberRepository, Member> implements MemberService{

	// Constructor
	public MemberServiceImpl(){
		super(MemberRepository.class,MemberResult.class);
	}
	
	@Override
	public MemberResult createMember(MemberResult memberResult)
			throws Exception {
		
		final Member me = memberResult.getMember();
		
		//Here have all validation necessary.
		if(me.getPassword() == null)
			me.setPassword("12345678");
		
		memberResult.setMember(this.getDao().save(me));
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
	
//	@Override
//	public Page<Member> paginationByName(String name, Pageable pageable) {
//		return this.getDao().findByName(name, pageable);
//	}
}
