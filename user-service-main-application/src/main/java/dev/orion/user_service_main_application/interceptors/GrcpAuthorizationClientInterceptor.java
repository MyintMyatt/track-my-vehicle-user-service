package dev.orion.user_service_main_application.interceptors;

import dev.orion.track_my_vehicle_auth_client.ServiceTokenManager;
import io.grpc.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrcpAuthorizationClientInterceptor implements ClientInterceptor {

    private ServiceTokenManager tokenManager;

    private static final Metadata.Key<String> AUTH_TOKEN =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);


    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            CallOptions callOptions,
            Channel next) {

        log.info("=============adding auth token.==================");
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(AUTH_TOKEN, tokenManager.getToken());
                super.start(responseListener, headers);
            }
        };
    }
}
