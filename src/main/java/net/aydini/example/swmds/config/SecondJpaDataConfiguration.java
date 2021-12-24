package net.aydini.example.swmds.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import net.aydini.example.swmds.dao.another.AnotherDbPersonDao;
import net.aydini.example.swmds.domain.entity.another.AnotherDbPersonEntity;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 */


@Configuration
@EnableJpaRepositories(basePackageClasses = AnotherDbPersonDao.class,
entityManagerFactoryRef = "secondEntityManagerFactory", transactionManagerRef = "secondTransactionManager")
public class SecondJpaDataConfiguration
{
	@Autowired
	private Environment env;


	@Bean
	public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(secondDataSource());
		em.setPackagesToScan(AnotherDbPersonEntity.class.getPackage().getName());
		

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		final HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.put("jpa.show-sql", env.getProperty("spring.jpa.show-sql"));
		
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.second-datasource")
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public PlatformTransactionManager secondTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(secondEntityManagerFactory().getObject());
		return transactionManager;
	}

	
	
	@Bean
	public NamedParameterJdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource")DataSource ds)
	{
		return new NamedParameterJdbcTemplate(ds);
	}
}


