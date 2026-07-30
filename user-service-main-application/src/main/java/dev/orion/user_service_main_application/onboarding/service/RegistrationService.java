package dev.orion.user_service_main_application.onboarding.service;

import com.ezsender.client.NotificationClient;
import dev.orion.commons.exception.BusinessException;
import dev.orion.commons.utils.PhoneValidator;
import dev.orion.core.domain.transaction.constant.TransactionState;
import dev.orion.user_domain.repository.EmployeeAccountRepo;
import dev.orion.user_service_main_application.client.AuthServerClient;
import dev.orion.user_service_main_application.onboarding.request.RegistrationRequest;
import dev.orion.user_service_main_application.onboarding.request.SetPasswordRequest;
import dev.orion.user_service_main_application.onboarding.response.RegistrationResponse;
import dev.orion.user_service_main_application.service.EmployeeAccountService;
import dev.orion.grpc.employee.RegisterRequest;
import dev.orion.grpc.notification.NotificationProfileRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    @Value("${app.email.domain.name}")
    private String emailDomainName;

    @Value("${spring.application.name}")
    private String serviceName;

    private final EmployeeAccountRepo accountRepo;
    private final EmployeeAccountService accountService;
    private final AuthServerClient authClient;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NotificationClient notificationClient;

    @Transactional
    public boolean  register(RegistrationRequest form) {
        // Check email with company domain name that is company mail or not
        if(!checkEmailDomainName(form.email())){
            throw new BusinessException("Your email is not company mail.");
        }

        PhoneValidator.validate(form.phone());
        if(accountService.checkAccountExisted(form.username())){
            throw new BusinessException("Username already taken.");
        }

        var account = form.entity();
//        accountRepo.save(account);

        // register notification profile
        notificationClient.registerNotificationProfile(
                NotificationProfileRegisterRequest.newBuilder()
                        .setUsername(account.getUsername())
                        .setEmail(account.getEmail())
                        .setPhone(account.getPhone())
                        .addDeviceInfo(form.deviceInfo().toGrpcRequest())
                        .setTenantId(serviceName)
                        .build()
        );
        return true;
    }

    private boolean checkEmailDomainName(String email){
        var arr = email.split("@");
        String domain = arr[1];
        return emailDomainName.equals(domain);
    }

    @Transactional
    public RegistrationResponse setNewPassword(SetPasswordRequest request) {
        var account = accountService.findByUsername(request.username());
        var response = authClient.register(
          RegisterRequest.newBuilder()
                  .setUsername(account.getUsername())
                  .setEmail(account.getEmail())
                  .setPhone(account.getPhone())
                  .setPassword(request.password())
                  .setFullName(account.getFullName())
                  .build()
        );

        if(response.getSuccess()){
            account.setTransactionState(TransactionState.SUCCESS);
            // TODO: send register successful push notification to client
        }else {
            account.setTransactionState(TransactionState.FAIL);
        }
        return new RegistrationResponse(request.username(), response.getMessage());
    }
}
