package org.church.core.dao;

import java.util.List;

public interface GenericDao<T> {

	public abstract T findById(Integer id);
	
	public abstract List<T> findAll();
	
	public abstract T insert(T entity);
	
	public abstract T update(T entity);
	
	public abstract void remove(T entity);
	
}
