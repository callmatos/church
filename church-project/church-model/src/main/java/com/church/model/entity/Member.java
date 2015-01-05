package com.church.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.Past;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.church.core.dao.GenericDaoObject;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NamedQuery(name = "Member.findByName", query = "from Member m where m.name = ?1")
@Data
@EqualsAndHashCode(callSuper=true)
public class Member extends GenericDaoObject {

	private static final long serialVersionUID = -804593313768940126L;

	@Column(unique = true) 
	private String name;
	
	//Get and set provided with Lombok
	private String userName;
	
	//Get and set provided with Lombok
	private String password;
	
	@OneToOne(optional=false,cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="id")
	private Address address;
	
	@Email
	private String email;
	
//	@DateTimeFormat(pattern = "MM/dd/yyyy")
//	@Past
//	private Date birthday;
	
//	private List<Decimate> listDecimates;
	
//	private TypeMember typeMember;
	
	//Default constructor
	public Member(){}
	
	// Constructor with parameter.
	public Member(String name){
		this.name = name;
	}
	
}
