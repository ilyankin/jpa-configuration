# JPA (Hibernate) Configuration Ways

You might say that configuring JPA this is not such a difficult task.
And you'll be absolutely right! There's a lot of article and guides about
it in the Internet. But often the configuration takes place separately from 
the main framework of many modern applications - **Spring Boot**!

In various guides, where this wonderful framework is still present, 
they talk only about one of the most trivial configuration methods, 
which is considered native for applications built on **Spring Boot** - using a
property file.

The Spring Boot is just a convenient and powerful wrapper around dependencies, 
the core of which is IoC-container. So the Spring developers went to great lengths 
to avoid breaking the native setup and configurations of individual dependencies!

## Native JPA configuration - [persistence.xml](raw-jpa%2Fsrc%2Fmain%2Fresources%2FMETA-INF%2Fpersistence.xml)

This configuration way was invented to unify the configuration of all ORMs 
that implemented JPA specification.

### Pros:
- **Standardization:** It's part of the JPA standard
- **Implementation independent:** Easily switch to another ORM-framework that implements the JPA specification

### Cons:
- **Verbosity:** The XML format can be verbose and more complex to read
- **Less Flexibility:** It doesn't support the feature of automatic scanning of entities 
contained in packages (All entities will have to be explicitly specified in the `persistence.xml`)


## 1. Native Hibernate configuration - [hibernate.properties](hibernate-jpa-properties%2Fsrc%2Fmain%2Fresources%2Fhibernate.properties)

The configuration by `hibernate.properties` file has been part of Hibernate since the earliest versions.
And contained limited functionality compared to `hibernate.cfg.xml`.
This is an ORM-specific solution!

### Pros:
- **Ease of Use:** The properties file is easy to read and modify than XML format
### Cons:
- **ORM-specific:** It works only for Hibernate ORM Framework
- **Limited settings:** Limited to basic settings such as database connection and basic Hibernate settings

## 2. Native Hibernate configuration - [hibernate.cfg.xml](hibernate-jpa-xml%2Fsrc%2Fmain%2Fresources%2Fhibernate.cfg.xml)

This configuration way was added by the Hibernate developers after configuration by `hibernate.properties` file
but before the development of the JPA specification began. Allows more flexible configuration and 
expansion of the configuration, including integration with other frameworks and technologies.
This is an ORM-specific solution.

### Pros:
- **Compatibility:** Fully compatible with all Hibernate-specific **features**

### Cons:
- **ORM-specific:** It works only for Hibernate ORM Framework
- **Verbosity:** The XML format can be verbose and more complex to read

## 1.Spring configuration - [application.yaml](spring-data-jpa%2Fsrc%2Fmain%2Fresources%2Fapplication.yaml)

The `application.properties`/`application.yaml` serves as a central place for managing application-level configuration 
settings for all dependencies which integrated with Spring Framework (Including Hibernate).

### Pros:
- **Ease of Use:** The properties file is easy to read and modify than XML format
- **Universality:** Support both general JPA properties and ORM-specific properties (settings)
- **ORM Implementation independent:** Easily switch to another ORM-framework that implements the JPA specification, 
If ORM-specific settings are not used
- **Automatic configuration:** The Spring automatically configures many useful things by default.
- **Environment variables injection:** The Spring can inject values for properties from environment variables

### Cons:
- **Framework-specific:** It works only with Spring

## 2.Spring configuration - java-based code
When you configure dependencies using `application.properties`/`application.yaml` files, the Spring creates all
the necessary bins for your application to work correctly. But of course you can override this automatic configuration 
and configure the beans yourself!

### Pros (compared to property file configuration)
- **Clearer Dependency Management:** When configuring beans manually, dependencies between beans are often more explicit.

### Cons (compared to property file configuration)
- **Increased Complexity:** Manual configuration can lead to more complex and verbose code
- **Lack of Standardization:** Manual configurations can lead issues with versioning of the framework
- **Reduced Flexibility:** With application.properties, it's easier to change configuration without altering the code, 
which is particularly useful in different environments (development, testing, production)
- **Lack of External Configuration:** application.properties allows for externalizing configuration, 
making it easier to modify without rebuilding the application