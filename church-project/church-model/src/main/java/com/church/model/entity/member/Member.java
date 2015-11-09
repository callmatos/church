package com.church.model.entity.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.church.core.dao.GenericDaoObject;
import org.hibernate.validator.constraints.Email;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@NamedQuery(name = "Member.findByName", query = "from Member m where m.name = ?1")
@Data	
@EqualsAndHashCode(callSuper=true)
public class Member extends GenericDaoObject {

	private static final long serialVersionUID = -804593313768940126L;

	@Column(unique = true) 
	private String name;
	
	//Get and set provided with Lombok Data
	private String userName;
	
	//Get and set provided with Lombok Data
	private String password;
	
	@OneToOne(optional=false,cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="id")
	private Address address;
	
	@Email
	private String email;
	
	//Default constructor
	public Member(){}
	
	// Constructor with parameter.
	public Member(String name){
		this.name = name;
	}
	
}
