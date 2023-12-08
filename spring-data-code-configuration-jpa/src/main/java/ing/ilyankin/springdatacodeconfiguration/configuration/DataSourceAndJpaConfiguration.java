package ing.ilyankin.springdatacodeconfiguration.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceAndJpaConfiguration {

    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl("");
        dataSourceProperties.setName("sa");
        dataSourceProperties.setPassword("jdbc:h2:file:./data/demo;AUTO_SERVER=TRUE");
        return dataSourceProperties;
    }

    @Bean
    public DataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(DataSource dataSource,
                                                                            JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource);
        lcemfb.setPackagesToScan("ing.ilyankin.springdatacodeconfiguration.model");
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcemfb.setJpaProperties(jpaProperties());
        return lcemfb;
    }

    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.format_sql", Boolean.TRUE);
        return properties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true); // hibernate.hbm2ddl.auto=update
        return hibernateJpaVendorAdapter;
    }
}
