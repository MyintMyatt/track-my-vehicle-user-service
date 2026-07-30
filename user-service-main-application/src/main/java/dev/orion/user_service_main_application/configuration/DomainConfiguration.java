package dev.orion.user_service_main_application.configuration;

import dev.orion.core.domain.repository.AbstractRepositoryImpl;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = {"dev.orion.user_domain.repository"},
        repositoryBaseClass = AbstractRepositoryImpl.class
)
@EntityScan(
        basePackages = "dev.orion.employee_domain.entity"
)
public class DomainConfiguration {
}
