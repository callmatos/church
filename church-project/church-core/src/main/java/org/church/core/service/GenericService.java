package org.church.core.service;

import org.springframework.data.domain.Page;

/**
 * This interface represent the base interface to construct Service.
 * 
 * @author charles.ma
 *
 * @param <DTO> Possible result class for response.
 * @param <DAO> Repository type domain class
 * @param <DOMAIN> Possible class of entity.
 */
public interface GenericService<DTO,DAO,DOMAIN> {

	//Method to return all Entity.
	public abstract DTO findAll();
	
	//Method to return Entity with id
	public abstract DTO findById(Integer id);
	
	//Method to pagination
	Page<DOMAIN> findPaginated(int page,int size);
	
	//Method to delete Entity with id
	public abstract void delete(Integer id);
}
