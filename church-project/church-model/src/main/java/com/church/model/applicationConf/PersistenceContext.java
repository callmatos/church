package com.church.model.applicationConf;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = { "classpath:META-INF/application.properties" })
@EnableJpaRepositories(basePackages = { "com.church.model.repository" })
@EnableTransactionManagement
public class PersistenceContext {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String PROPERTY_NAME_HBM2DLL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_NAME_STRATEGY = "hibernate.ejb.naming_strategy";
	private static final String PROPERTY_NAME_LAZY= "hibernate.enable_lazy_load_no_trans";
	
	@Resource
	private Environment env;

	//Constructor default.
	public PersistenceContext(){
		super();
	}
	
	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource ddSource = new DriverManagerDataSource();

		ddSource.setDriverClassName(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		ddSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		ddSource.setUsername(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		ddSource.setPassword(env
				.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return ddSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());

		entityManagerFactoryBean
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // setPersistenceProviderClass(HibernatePersistence.class);
		entityManagerFactoryBean
				.setPackagesToScan(env
						.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

		entityManagerFactoryBean.setJpaProperties(hibProperties());

		return entityManagerFactoryBean;
	}

	// Retrieve the property to hibernate.
	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_HBM2DLL_AUTO,
				env.getRequiredProperty(PROPERTY_NAME_HBM2DLL_AUTO));
		properties.put(PROPERTY_NAME_STRATEGY,
				env.getRequiredProperty(PROPERTY_NAME_STRATEGY));
		properties.put(PROPERTY_NAME_LAZY, env.getRequiredProperty(PROPERTY_NAME_LAZY));

		return properties;
	}
	
	@Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
