package org.church.core.dao;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.jpa.domain.AbstractPersistable;


@JsonIgnoreProperties({"id"})
public class GenericDaoObject extends AbstractPersistable<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3236700618814324143L;
	
	@JsonIgnore
	public Integer getChurchId;


	public Integer getGetChurchId() {
		return getChurchId;
	}

	public void setGetChurchId(Integer getChurchId) {
		this.getChurchId = getChurchId;
	}
	

	@Override
	public String toString() {
		
		String jsonString = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		try{
			jsonString = mapper.writeValueAsString(this);
		} catch(Exception e){
			jsonString = null;
		}
		return jsonString;
	}
	
	
	
}
