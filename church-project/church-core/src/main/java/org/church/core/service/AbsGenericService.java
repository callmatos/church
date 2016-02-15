package org.church.core.service;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is a abstract because have all method commum to Service's object.
 * 
 * @author charles.ma
 *
 * @param <DTO> - Type of object of model.
 * @param <DAO> - The Repository
 * @param <DOMAIN>
 */
public abstract class AbsGenericService<DTO extends GenericResult<DOMAIN>, DAO extends JpaRepository<DOMAIN, Integer>, DOMAIN> implements GenericService<DTO, DAO, DOMAIN>, ApplicationContextAware,InitializingBean {

	
	// Variable
	private Class<DAO> daoClass;
	private Class<DTO> dtoClass;
	private DAO dao;
	private DTO dto;
	
	private ApplicationContext applicationContext;
	
	/**
	 * Default Constructor
	 */
	protected AbsGenericService() {
	}
	
	/**
	 * This method return the object DAO(MemberRepository.class)
	 * @return
	 */
	public Class<DAO> getDaoClass() {
		return daoClass;
	}
	
	/**
	 * This method return the Repository configured.
	 * 
	 * @return
	 */
	public DAO getDao(){
		return this.dao;
	}
	
	
	/**
	 * This method inject the application context
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	/**
	 * Constructor with paramether.
	 * 
	 * @param daoClass - Repository Class
	 * @param dtoClass - Class of model.
	 */
	protected AbsGenericService(Class<DAO> daoClass, Class<DTO> dtoClass) {
		this.daoClass = daoClass;
		this.dtoClass = dtoClass;
	}
	
	/**
	 * Inject the DAO and DTO object.
	 */
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
	 * Method to domain crude for all services.
	 */
	@Override
	@Transactional(readOnly = true)
	public DTO findAll() {
		dto.setResult(dao.findAll());
		return dto;
	}
	
	/**
	 * Find object by ID
	 */
	@Override
	@Transactional(readOnly = true)
	public DTO findById(Integer id) {
		dto.setResult(dao.findOne(id));
		return dto;
	}
	
	/**
	 * Return list paginated.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<DOMAIN> findPaginated(Pageable pagination) {
		
		return this.dao.findAll(pagination);
	}
	
	/**
	 * Delete object by ID.
	 */
	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}
	
}
