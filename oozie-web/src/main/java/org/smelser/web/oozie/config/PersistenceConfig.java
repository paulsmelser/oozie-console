package org.smelser.web.oozie.config;//package org.smelser.web.oozie.config;
//import java.util.Properties;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.init.DataSourceInitializer;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages="com.smelser.org.persistence.repository")
//public class PersistenceConfig 
//{
//	@Autowired
//	private Environment env;
//	private static final Log logger = LogFactory.getLog(PersistenceConfig.class);
//	@Value("${init-db:true}")
//	private String initDatabase;
//	
//	
//	@Bean
//	public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
//	{
//		return new JpaTransactionManager(emf);
//	}
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
//	{
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(Boolean.TRUE);
//		vendorAdapter.setShowSql(Boolean.TRUE);
//		em.setDataSource(dataSource);
//		em.setJpaVendorAdapter(vendorAdapter);
//		em.setPackagesToScan(new String[] {"com.smelser.org.persistence.entity"});
//		em.setJpaProperties(additionalProperties());
//		em.afterPropertiesSet();
//		em.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
//		return em;
//	}
//	@Bean
//	public HibernateExceptionTranslator hibernateExceptionTranslator()
//	{
//		return new HibernateExceptionTranslator();
//	}
//	@Bean
//	public DataSource dataSource() throws Exception
//	{
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		try {
//			dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//			dataSource.setUrl(env.getProperty("jdbc.url"));
//			dataSource.setUsername(env.getProperty("jdbc.username"));
//			dataSource.setPassword(env.getProperty("jdbc.password"));
//		}
//		catch(Exception exception){
//			logger.error(String.format("Error initiating Database %-1s - %-2s", exception.getMessage(), exception.getStackTrace()));
//			throw exception;
//		}
//		return dataSource;
//	}
//	@Bean
//	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) 
//	{
//		logger.info(Boolean.parseBoolean(initDatabase));
//		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//		dataSourceInitializer.setDataSource(dataSource);
//		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
//		databasePopulator.addScript(new ClassPathResource("db.sql"));
//		dataSourceInitializer.setDatabasePopulator(databasePopulator);
//		dataSourceInitializer.setEnabled(false);
//		return dataSourceInitializer;
//	}	
//	Properties additionalProperties() {
//	      Properties properties = new Properties();
//	      properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//	      properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
//	      return properties;
//	   }
//}