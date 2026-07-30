package dev.orion.user_service_main_application.onboarding.request;

import jakarta.validation.constraints.NotBlank;

public record SetPasswordRequest (
        @NotBlank(message = "username is required.") String username,
        @NotBlank(message = "password is required.") String password
){
}
