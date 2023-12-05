package ing.ilyankin.rawspring.configuration;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.FileInputStream;
import java.util.Properties;

@Configuration
public class HibernateJpaPropertiesConfiguration {

    @SneakyThrows
    @Bean("entityManagerFactory") // this bean name is mandatory to apply @PersistenceContext annotation support
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("ing.ilyankin.rawspring.model");
        Properties hibernateProperties = new Properties();
        hibernateProperties.load(new FileInputStream("./src/main/resources/hibernate.properties"));
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }
}
