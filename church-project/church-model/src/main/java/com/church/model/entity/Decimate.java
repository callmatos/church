package com.church.model.entity;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.church.core.dao.GenericDaoObject;

import com.church.model.entity.member.Month;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class Decimate extends GenericDaoObject{

	private static final long serialVersionUID = 2515469099258091809L;

	// Data reference the deliver(Jan,Feb, Mar...)
	//Ex. 2015/01/15 
	private Long deliverDecimate;
	
	// Deliver of month.
	private Month month;
	
	// How much.
	private double value;
	
	//Default constructor
	public Decimate(){}
	
	// Constructor with parameter.
	public Decimate(Long deliverDecimate, Month month, double value){
		this.deliverDecimate = deliverDecimate;
		this.month = month;
		this.value = value;
	}
	
}
