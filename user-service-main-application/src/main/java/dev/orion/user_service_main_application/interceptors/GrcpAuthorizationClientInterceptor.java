package dev.orion.user_service_main_application.interceptors;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrcpAuthorizationClientInterceptor implements ClientInterceptor {

    private static final Metadata.Key<String> AUTH_TOKEN =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            CallOptions callOptions,
            Channel next) {

        log.info("=============adding auth token.==================");
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT,RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(AUTH_TOKEN, "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluIiwiaXNzIjoiZGV2Lm9yaW9uLmF1dGgiLCJpYXQiOjE3ODUzMTI2OTcsImV4cCI6MTc4NTMyNzA5Nywicm9sIjoiMixTVVBFUl9BRE1JTixGQUNUT1JfUEFTU1dPUkQiLCJ0eXAiOiJSZWZyZXNoIn0.aexhOr7o61rDOncXNbLqIH3UzBYCS-CjX4bVYgDMjpmQXkwN0U7wTzIQ0o6EnquKqZ0CyXEsIRceprlQObo81TbjS3zYS26E8YUrMZYEzKZTUJ2VrDqnhnv0qvVtYDso1zedFK1zvf_qeqHnB3mMNFV1Nd2zcFrc0TBQ9ZV3XYcwodCXf4r-uG8Z5iJqUsw7KCM7cT8uIMrAeSRk7AT-EkW7KBcINRgYSVdcXxvu9AelARKbqm3G-UM3BOu9YDDkiWP12nKssRNyUTt4kGKAgV9gXMTPIzRLe0VuKdu0ZzbA59kjEHsVN-riVnNpagg_mE7cqQwEl3xm7BWpIvfuEA");
                super.start(responseListener, headers);
            }
        };
    }
}
