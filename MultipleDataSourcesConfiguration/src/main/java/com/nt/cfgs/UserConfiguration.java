package com.nt.cfgs;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;
@Configuration
@EnableJpaRepositories(basePackages = "com.nt.user.repo",entityManagerFactoryRef = "creaeEntityManagerFactory2",transactionManagerRef = "createPlatForm2")

@EnableTransactionManagement
public class UserConfiguration {
	
	@Autowired
	private Environment m;

	// 1.creating Datasource

	@Bean("userDs")
	
	public DataSource createDataSource() {
		
		HikariDataSource data=new HikariDataSource();
		data.setUsername("root");
		data.setPassword("1234");	
		data.setDriverClassName("com.mysql.cj.jdbc.Driver");
		data.setJdbcUrl("jdbc:mysql:///user");
		System.out.println("=============="+data.getClass());
		return data;
	}

	// 2.CreateEntityManager factory
	@Bean(name="creaeEntityManagerFactory2")
	public LocalContainerEntityManagerFactoryBean creaeEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("userDs") DataSource ds) {
		Map<String, String> props = new HashMap<>();
		  props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		    props.put("hibernate.hbm2ddl.auto", "update");
		    props.put("jpa.show-sql", "true");
		return builder.dataSource(ds).properties(props).packages("com.nt.user.entity").build();
	}
	//3.TransactonManager
	@Bean("createPlatForm2")
	public PlatformTransactionManager createPlatForm(@Qualifier("creaeEntityManagerFactory2") EntityManagerFactory factory) {
	return new JpaTransactionManager(factory);
	}
	
	

}
