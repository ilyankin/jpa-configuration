rootProject.name = "jpa-configuration"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("raw-jpa")
include("hibernate-jpa-xml")
include("hibernate-jpa-properties")
include("spring-data-jpa")