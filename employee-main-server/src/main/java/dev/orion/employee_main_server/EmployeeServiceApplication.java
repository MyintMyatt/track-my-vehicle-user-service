package dev.orion.employee_main_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class EmployeeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }
}