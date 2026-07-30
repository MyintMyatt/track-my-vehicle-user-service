package dev.orion.user_service_main_application.onboarding;

import dev.orion.commons.model.ApiResponse;
import dev.orion.user_service_main_application.onboarding.request.RegistrationRequest;
import dev.orion.user_service_main_application.onboarding.request.SetPasswordRequest;
import dev.orion.user_service_main_application.onboarding.response.RegistrationResponse;
import dev.orion.user_service_main_application.onboarding.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/v1/")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService service;

    @PostMapping("/register")
    public ApiResponse<Boolean> register(@Valid @RequestBody RegistrationRequest form, BindingResult result){
        return ApiResponse.success(service.register(form));
    }

    @PostMapping("/set-password")
    public ApiResponse<RegistrationResponse> setPassword(
            @Valid @RequestBody SetPasswordRequest request, BindingResult result
            ){
        return ApiResponse.success(service.setNewPassword(request));
    }
}
