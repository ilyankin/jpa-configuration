package ing.ilyankin.hibernatejpaproperties.configuration;

import lombok.SneakyThrows;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.FileInputStream;
import java.util.Properties;

@Configuration
public class HibernateJpaPropertiesConfiguration {

//    @SneakyThrows
//    @Bean("entityManagerFactory") // this bean name is mandatory to apply @PersistenceContext annotation support
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setPackagesToScan("ing.ilyankin.hibernatejpaproperties.model");
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.load(new ClassPathResource("hibernate.properties").getInputStream());
//        sessionFactory.setHibernateProperties(hibernateProperties);
//        return sessionFactory;
//    }

    @SneakyThrows
    @Bean("entityManagerFactory") // this bean name is mandatory to apply @PersistenceContext annotation support
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("ing.ilyankin.hibernatejpaproperties.model");
        sessionFactory.setHibernateProperties(Environment.getProperties());
        return sessionFactory;
    }
}
