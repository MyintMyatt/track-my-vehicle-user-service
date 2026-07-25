package dev.orion.employee_main_server.onboarding;

import dev.orion.commons.model.ApiResponse;
import dev.orion.employee_main_server.onboarding.request.RegistrationRequest;
import dev.orion.employee_main_server.onboarding.service.RegistrationService;
import dev.orion.grpc.employee.RegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/v1/")
@RequiredArgsConstructor
public class RegistrationController {

    private RegistrationService service;

    @PostMapping("/register")
    public ApiResponse<Boolean> register(@Valid @RequestBody RegistrationRequest form, BindingResult result){
        return ApiResponse.success(service.register(form));
    }

    @PostMapping("/set-password")
    public ApiResponse<RegisterResponse> setPassword(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
            ){
        return ApiResponse.success(service.setNewPassword(username, password));
    }
}
