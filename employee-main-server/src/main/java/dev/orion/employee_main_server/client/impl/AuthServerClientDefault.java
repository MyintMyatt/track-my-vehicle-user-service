package dev.orion.employee_main_server.client.impl;

import dev.orion.employee_main_server.client.AuthServerClient;
import dev.orion.grpc.employee.EmployeeRegisterServiceGrpc;
import dev.orion.grpc.employee.RegisterRequest;
import dev.orion.grpc.employee.RegisterResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class AuthServerClientDefault implements AuthServerClient {

    private final EmployeeRegisterServiceGrpc.EmployeeRegisterServiceStub stub;

    @Override
    public RegisterResponse register(RegisterRequest request) {
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
        return result.join();
    }
}
