package com.church.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.church.core.dao.GenericDaoObject;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class Address extends GenericDaoObject {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 2981474752170498796L;

	/**
	 * All get and set methods are created with lombok.
	 */
	private String address;
	
	private String number;
	
	private String state;
	
	private String country;
	
	/**
	 * Default constructor
	 */
	public Address(){}
	
	/**
	 * Construct with parameter.
	 * 
	 * @param address
	 * @param number
	 * @param state
	 * @param country
	 */
	public Address(String address, String number, String state, String country){
		this.address = address;
		this.number = number;
		this.state = state;
		this.country = country;
	}
	
}
