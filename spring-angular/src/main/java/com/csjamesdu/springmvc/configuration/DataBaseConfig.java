package com.csjamesdu.springmvc.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:config/application.properties")
public class DataBaseConfig {
	
	 private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	 private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	 private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	 private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	 
	 @Resource
	 private Environment environ;
	 
	 @Bean
	 public DataSource getDataSource() {
		 final BasicDataSource dataSource = new BasicDataSource();
		 
		 dataSource.setDriverClassName(environ.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		 dataSource.setUrl(environ.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		 dataSource.setUsername(environ.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		 dataSource.setPassword(environ.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		 
		 return dataSource;
	 }
	 
	 @Bean Properties getHibernateProperties() {
		 Properties hibernateProperties = new Properties();
		 hibernateProperties.setProperty("hibernate.show_sql", "true");
		 hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		 hibernateProperties.setProperty("hibernate.format_sql", "true");
		 hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		 hibernateProperties.setProperty("hibernate.packagesToScan", "com.csjamesdu.springmvc.model");
		 return hibernateProperties;
	 }
	 
	 @Bean(name="LocalSessionFactoryBean")
	 public LocalSessionFactoryBean sessionFactory() {
		 LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		 sessionFactory.setDataSource(getDataSource());
		 sessionFactory.setHibernateProperties(getHibernateProperties());
//		 sessionFactory.setAnnotatedPackages("com.csjamesdu.springmvc.model");
//		 sessionFactory.setAnnotatedClasses(com.csjamesdu.springmvc.model.User.class);//if without, cannot map db to model;
		 sessionFactory.setPackagesToScan("com.csjamesdu.springmvc.model");//can serve as package level substitution of the above config;
		 
		 return sessionFactory;
	 }
}
