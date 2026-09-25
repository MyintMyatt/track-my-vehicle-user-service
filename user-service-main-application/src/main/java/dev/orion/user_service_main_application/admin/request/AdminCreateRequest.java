package dev.orion.user_service_main_application.admin.request;

import jakarta.validation.constraints.NotBlank;

public record AdminCreateRequest(
        @NotBlank(message = "email required")
        String email,

        @NotBlank(message = "full name required")
        String fullName,

        @NotBlank(message = "user name required")
        String username,

        @NotBlank(message = "employee id required")
        String employeeId,
        Long roleId
) {
}
