package com.church.model.member.service;

import java.util.List;

import lombok.Data;

import org.church.core.service.GenericResult;
import org.springframework.stereotype.Component;

import com.church.model.entity.member.Member;

@Data
@Component
public class MemberResult implements GenericResult<Member> {

	private Member member;
	private List<Member> members;
	
	//Default constructor
	public MemberResult(){}
	
	// Constructor pass the member.
	public MemberResult(Member member){
		this.member = member;
	}
	
	@Override
	public void setResult(Member entity) {
		this.member = entity;
	}

	@Override
	public void setResult(List<Member> list) {
		this.members = list;
	}

	@Override
	public Member getTarget() {
		return this.member;
	}

}
