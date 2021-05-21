package com.roman.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Profile("prod")
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.roman"})
public class HibernateConfigProdConfig {


    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("com.roman")
                .addProperties(hibernateProperties());
        return builder.buildSessionFactory();
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSourceConfig = new DriverManagerDataSource();
        dataSourceConfig.setDriverClassName("org.postgresql.Driver");
        dataSourceConfig.setUrl("jdbc:postgresql://localhost:5432/shop");
        dataSourceConfig.setUsername("postgres");
        dataSourceConfig.setPassword("54321");
        return dataSourceConfig;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource ds) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(ds);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
        return hibernateProperties;
    }
}


