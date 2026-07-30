package dev.orion.user_service_main_application.client.impl;

import dev.orion.user_service_main_application.client.AuthServerClient;
import dev.orion.grpc.employee.EmployeeRegisterServiceGrpc;
import dev.orion.grpc.employee.RegisterRequest;
import dev.orion.grpc.employee.RegisterResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
public class AuthServerClientDefault implements AuthServerClient {

    private final EmployeeRegisterServiceGrpc.EmployeeRegisterServiceStub stub;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        log.info("============GRPC: call to auth service");
        var result = new CompletableFuture<RegisterResponse>();
        stub.register(request, new StreamObserver<RegisterResponse>() {
            @Override
            public void onNext(RegisterResponse value) {
                result.complete(value);
            }

            @Override
            public void onError(Throwable t) {
                result.completeExceptionally(t);
            }

            @Override
            public void onCompleted() {}
        });
        log.info("============GRPC: done result");
        return result.join();
    }
}
