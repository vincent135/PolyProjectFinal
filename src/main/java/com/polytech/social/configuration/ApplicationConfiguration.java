package com.polytech.social.configuration;

import com.polytech.social.generic.User;
import com.polytech.social.generic.Role;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
@EntityScan(basePackageClasses = {User.class, Role.class})
@ComponentScan(basePackageClasses = {User.class, Role.class})
@EnableJpaRepositories(basePackages = {"com.polytech.repository", "com.polytech.social.repository"})
public class ApplicationConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	@Profile("DEV")
	public DataSource dataSourceForDEV(){
		System.err.println("#..# EMBEDED DATABASE USED FOR DEV PROFILE");
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2).addScript("create-schema.sql")
			.build();
	}

	@Bean
	@Profile("PROD")
	public DataSource dataSourceForPROD(){
		BasicDataSource dataSource = new BasicDataSource();

		System.err.println("#..# MYSQL DATABASE USED FOR PROD PROFILE");

		dataSource.setDriverClassName(environment.getProperty("datasource.drivername"));

		dataSource.setUrl(environment.getProperty("datasource.url"));

		dataSource.setUsername(environment.getProperty("datasource.username"));
		dataSource.setPassword(environment.getProperty("datasource.password"));
		return dataSource;
	}

	/* Suppression après ajout de component au dessus des class instanciées -> auto scan des beans
	@Bean
	public PostRepository postRepository(DataSource dataSource){
		return new jdbcPostRepository(dataSource);
	}

	@Bean
	public PublicationService publicationService(PostRepository postRepository){
		return new PublicationServiceImpl(postRepository);
	}
	*/

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
									   JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan("com.polytech.generic", "com.polytech.social.generic");
		return emfb;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
		return adapter;
	}
}
