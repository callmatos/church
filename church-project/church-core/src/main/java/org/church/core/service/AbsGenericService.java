package org.church.core.service;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbsGenericService<DTO extends GenericResult<DOMAIN>, DAO extends JpaRepository<DOMAIN, Integer>, DOMAIN> implements GenericService<DTO, DAO, DOMAIN>, ApplicationContextAware,InitializingBean {

	
	private Class<DAO> daoClass;
	private Class<DTO> dtoClass;
	private DAO dao;
	private DTO dto;
	private ApplicationContext applicationContext;
	
	
	public Class<DAO> getDaoClass() {
		return daoClass;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	protected AbsGenericService() {
	}
	
	
	protected AbsGenericService(Class<DAO> daoClass, Class<DTO> dtoClass) {
		this.daoClass = daoClass;
		this.dtoClass = dtoClass;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(dao == null){
			dao = getBeanByType(applicationContext,daoClass);
			dto = getBeanByType(applicationContext,dtoClass);
		}
	}
	
	public static <T> T getBeanByType(ApplicationContext applicationContext, Class<T> clazz) {
		Map<String, T> beanMap = applicationContext.getBeansOfType(clazz);
		int size = beanMap.size();
		if (size == 0) {
			if (applicationContext.getParent() != null) return getBeanByType(applicationContext.getParent(), clazz);
			throw new NoSuchBeanDefinitionException(clazz.getSimpleName());
		}
		if (size > 1) throw new NoSuchBeanDefinitionException("No unique bean definition type [" + clazz.getSimpleName() + "]");
		return beanMap.values().iterator().next();
	}
	
	/**
	 * Method to domain crud for all services.
	 */
	
	@Override
	@Transactional(readOnly = true)
	public DTO findAll() {
		dto.setResult(dao.findAll());
		return dto;
	}
	
	@Override
	@Transactional(readOnly = true)
	public DTO findById(Integer id) {
		dto.setResult(dao.findOne(id));
		return dto;
	}
	
	@Override
	public Page<DOMAIN> findPaginated(int page, int size) {
		return this.dao.findAll(new PageRequest(page,size));
	}
	
	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}
	
}
