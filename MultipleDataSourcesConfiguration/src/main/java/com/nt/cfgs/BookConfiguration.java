package com.nt.cfgs;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.nt.book.repo",entityManagerFactoryRef = "creaeEntityManagerFactory",transactionManagerRef = "createPlatForm")

@EnableTransactionManagement
public class BookConfiguration {

	// 1.creating Datasource

	@Bean("bookDs")
	@Primary
	@ConfigurationProperties(prefix = "book.datasource")
	public DataSource createDataSource() {
		return DataSourceBuilder.create().build();
	}

	// 2.CreateEntityManager factory
	@Bean(name="creaeEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean creaeEntityManagerFactory(EntityManagerFactoryBuilder builder
			) {
		Map<String, String> props = new HashMap<>();
		  props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		    props.put("hibernate.hbm2ddl.auto", "update");
		    props.put("jpa.show-sql", "true");

		return builder.dataSource(createDataSource()).properties(props).packages("com.nt.book.entity").build();
	}
	//3.TransactonManager
	@Bean("createPlatForm")
	@Primary
	public PlatformTransactionManager createPlatForm(@Qualifier("creaeEntityManagerFactory") EntityManagerFactory factory) {
	return new JpaTransactionManager(factory);
	}
	
	

}
