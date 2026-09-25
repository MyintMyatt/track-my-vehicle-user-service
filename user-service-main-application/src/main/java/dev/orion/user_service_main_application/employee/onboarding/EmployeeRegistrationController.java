package dev.orion.user_service_main_application.employee.onboarding;

import dev.orion.commons.model.ApiResponse;
import dev.orion.user_service_main_application.employee.onboarding.request.EmployeeRegistrationRequest;
import dev.orion.user_service_main_application.employee.onboarding.request.SetPasswordRequest;
import dev.orion.user_service_main_application.employee.onboarding.response.RegistrationResponse;
import dev.orion.user_service_main_application.employee.onboarding.service.EmployeeRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/v1/employee")
@RequiredArgsConstructor
public class EmployeeRegistrationController {

    private final EmployeeRegistrationService service;

    @PostMapping("/register")
    public ApiResponse<Boolean> register(@Valid @RequestBody EmployeeRegistrationRequest form, BindingResult result){
        return ApiResponse.success(service.register(form));
    }

    @PostMapping("/set-password")
    public ApiResponse<RegistrationResponse> setPassword(
            @Valid @RequestBody SetPasswordRequest request, BindingResult result
            ){
        return ApiResponse.success(service.setNewPassword(request));
    }
}
