package dev.orion.user_service_main_application.client;

import dev.orion.grpc.employee.RegisterRequest;
import dev.orion.grpc.employee.RegisterResponse;

///
/// This client is only for employee service
///
public interface AuthServerClient {
    RegisterResponse register(RegisterRequest request);
}
