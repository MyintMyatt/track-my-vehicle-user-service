package dev.orion.user_service_main_application.configuration;

import dev.orion.user_service_main_application.interceptors.GrcpAuthorizationClientInterceptor;
import io.grpc.ClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GlobalClientInterceptor;

@Configuration
public class GrpcClientSecurityConfig {

    @Bean
    @GlobalClientInterceptor
    public ClientInterceptor authTokenInterceptor(){
        return new GrcpAuthorizationClientInterceptor();
    }

}
