package dev.orion.user_service_main_application.onboarding.request;

import dev.orion.commons.constant.Gender;
import dev.orion.commons.model.DeviceInfo;
import dev.orion.user_domain.entity.EmployeeAccount;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegistrationRequest(
        @NotBlank(message = "full name required")
        String fullName,
        @NotBlank(message = "unique username required")
        String username,
        @NotBlank(message = "email required")
        String email,
        @NotBlank(message = "phone required")
        String phone,
        @NotNull(message = "dob required")
        LocalDate dob,
        @NotNull(message = "unique username required")
        Gender gender,
        @NotBlank(message = "employee id required")
        String employeeId, // this id is assigned from company employee management system, not from this system.
        @Valid @NotNull(message = "device info required")
        DeviceInfo deviceInfo
) {
        public EmployeeAccount entity(){
                var account = new EmployeeAccount();
                account.setFullName(fullName);
                account.setUsername(username);
                account.setEmail(email);
                account.setPhone(phone);
                account.setDateOfBirth(dob);
                account.setGender(gender);
                account.setEmployeeId(employeeId);
                return account;
        }
}