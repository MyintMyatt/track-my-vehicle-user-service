package dev.orion.employee_main_server.configuration;

import dev.orion.employee_main_server.client.AuthServerClient;
import dev.orion.employee_main_server.client.impl.AuthServerClientDefault;
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
