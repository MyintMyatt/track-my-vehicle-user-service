package dev.orion.employee_main_server.onboarding.service;

import dev.orion.commons.exception.BusinessException;
import dev.orion.commons.utils.PhoneValidator;
import dev.orion.core.domain.transaction.constant.TransactionState;
import dev.orion.employee_domain.repository.EmployeeAccountRepo;
import dev.orion.employee_main_server.client.AuthServerClient;
import dev.orion.employee_main_server.onboarding.request.RegistrationRequest;
import dev.orion.employee_main_server.service.EmployeeAccountService;
import dev.orion.grpc.employee.RegisterRequest;
import dev.orion.grpc.employee.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Transactional
    public boolean  register(RegistrationRequest form) {
        // Check email with company domain name that is company mail or not
        if(!checkEmailDomainName(form.email())){
            throw new RuntimeException("Your email is not company mail.");
        }

        if(accountService.checkAccountExisted(form.username())){
            throw new BusinessException("Username already taken.");
        }

        PhoneValidator.validate(form.phone());
        var account = form.entity();
        accountRepo.save(account);
        return true;
    }

    private boolean checkEmailDomainName(String email){
        var arr = email.split("@");
        String domain = arr[1];
        return emailDomainName.equals(domain);
    }

    public RegisterResponse setNewPassword(String username, String password) {
        var account = accountService.findByUsername(username);
        var response = authClient.register(
          RegisterRequest.newBuilder()
                  .setUsername(account.getUsername())
                  .setEmail(account.getEmail())
                  .setPhone(account.getPhone())
                  .setPassword(password)
                  .build()
        );

        if(response.getSuccess()){
            account.setTransactionState(TransactionState.SUCCESS);
        }else {
            account.setTransactionState(TransactionState.FAIL);
        }
        return response;
    }
}
