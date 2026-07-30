package dev.orion.user_service_main_application.configuration;

import dev.orion.user_service_main_application.client.AuthServerClient;
import dev.orion.user_service_main_application.client.impl.AuthServerClientDefault;
import dev.orion.grpc.employee.EmployeeRegisterServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcBeanConfiguration {

    @Bean
    EmployeeRegisterServiceGrpc.EmployeeRegisterServiceStub registerServiceStub(GrpcChannelFactory factory){
        return EmployeeRegisterServiceGrpc.newStub(factory.createChannel("auth-service"));
    }

    @Bean
    public AuthServerClient authServerClient(EmployeeRegisterServiceGrpc.EmployeeRegisterServiceStub stub){
        return new AuthServerClientDefault(stub);
    }
}
