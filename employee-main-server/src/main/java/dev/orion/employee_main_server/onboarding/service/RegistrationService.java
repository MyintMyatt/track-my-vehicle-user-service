package dev.orion.employee_main_server.onboarding.service;

import dev.orion.commons.exception.BusinessException;
import dev.orion.commons.utils.PhoneValidator;
import dev.orion.core.domain.transaction.constant.TransactionState;
import dev.orion.employee_domain.repository.EmployeeAccountRepo;
import dev.orion.employee_main_server.client.AuthServerClient;
import dev.orion.employee_main_server.onboarding.request.RegistrationRequest;
import dev.orion.employee_main_server.onboarding.response.RegistrationResponse;
import dev.orion.employee_main_server.service.EmployeeAccountService;
import dev.orion.grpc.employee.RegisterRequest;
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

    private final EmployeeAccountRepo accountRepo;
    private final EmployeeAccountService accountService;
    private final AuthServerClient authClient;
    private final KafkaTemplate<String, String> kafkaTemplate;

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
        accountRepo.save(account);
        return true;
    }

    private boolean checkEmailDomainName(String email){
        var arr = email.split("@");
        String domain = arr[1];
        return emailDomainName.equals(domain);
    }

    @Transactional
    public RegistrationResponse setNewPassword(String username, String password) {
        var account = accountService.findByUsername(username);
        var response = authClient.register(
          RegisterRequest.newBuilder()
                  .setUsername(account.getUsername())
                  .setEmail(account.getEmail())
                  .setPhone(account.getPhone())
                  .setPassword(password)
                  .setFullName(account.getFullName())
                  .build()
        );

        if(response.getSuccess()){
            account.setTransactionState(TransactionState.SUCCESS);
            // TODO: send register successful push notification to client
        }else {
            account.setTransactionState(TransactionState.FAIL);
        }
        return new RegistrationResponse(username, response.getMessage());
    }
}
