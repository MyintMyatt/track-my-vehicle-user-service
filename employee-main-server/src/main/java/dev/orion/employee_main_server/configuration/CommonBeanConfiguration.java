package dev.orion.employee_main_server.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "dev.orion.commons"
})
public class CommonBeanConfiguration {
}
