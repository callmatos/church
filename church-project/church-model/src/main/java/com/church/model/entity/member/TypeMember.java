package com.church.model.entity.member;

import javax.persistence.Entity;

import org.church.core.dao.GenericDaoObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class TypeMember extends GenericDaoObject{
	
	private static final long serialVersionUID = 4894838709976826673L;
	
	private String description;

	public TypeMember(){}
	
	public TypeMember(String description){
		this.description = description;
	}
}
