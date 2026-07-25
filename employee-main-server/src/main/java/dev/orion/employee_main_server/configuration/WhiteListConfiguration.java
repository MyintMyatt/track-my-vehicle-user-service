package dev.orion.employee_main_server.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.security")
@Data
public class WhiteListConfiguration {

    @Value("${app.security.white.list}")
    private List<String> whiteList;
    
}
