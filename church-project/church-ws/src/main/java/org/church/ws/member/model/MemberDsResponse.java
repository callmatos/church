package org.church.ws.member.model;

import java.util.List;

import org.church.core.generic.model.DsReponse;

import com.church.model.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MemberDsResponse extends DsReponse{

	// Member object to return when find only member.
	private Member member;

	// Return the list of members
	private List<Member> members;
	
	/**
	 * Default constructor.
	 */
	public MemberDsResponse(){}

	/**
	 * Get member
	 * 
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Get members
	 * 
	 * @param member list.
	 */
	public List<Member> getMembers() {
		return members;
	}


	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
}
