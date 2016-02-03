package com.church.model.entity.message;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * This class represent the entity data to send to ActiveMQ service.
 * 
 * @author charles.ma
 *
 */

@Data
public class ElectricityMessage implements Serializable {
	

	private static final long serialVersionUID = 2033532177174690247L;

	//Owner ID
	private String ownerId;
	
	//Time requested
	private Timestamp createDateTime;
	
	//Value of consume
	private Double consume;
	
	//Constructor with parameter.
	public ElectricityMessage(String ownerId, Timestamp create, Double consume){
		super();
		this.ownerId = ownerId;
		this.createDateTime = create;
		this.consume = consume;
	}
	
}
