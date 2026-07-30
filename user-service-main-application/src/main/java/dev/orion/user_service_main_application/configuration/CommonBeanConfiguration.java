package dev.orion.user_service_main_application.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "dev.orion.commons"
})
public class CommonBeanConfiguration {
}
