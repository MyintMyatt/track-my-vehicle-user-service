package dev.orion.user_service_main_application.employee.onboarding.request;

import dev.orion.commons.constant.Gender;
import dev.orion.commons.model.DeviceInfo;
import dev.orion.user_domain.embedded.UserName;
import dev.orion.user_domain.entity.AccountNotificationPreferences;
import dev.orion.user_domain.entity.EmployeeAccount;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record EmployeeRegistrationRequest(
        @NotBlank(message = "full name required")
        String fullName,
        @NotBlank(message = "unique username required")
        String username,
        @NotBlank(message = "email required")
        String email,
        @NotEmpty(message = "phone required")
        List<String> phone,
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
                var userName = new UserName();
                userName.toUserName(username);
                var account = new EmployeeAccount();
                account.setFullName(fullName);
                account.setUserName(userName);
                account.setEmail(email);
                account.setPhone(phone);
                account.setDateOfBirth(dob);
                account.setGender(gender);
                account.setEmployeeId(employeeId);
                return account;
        }

//        public dev.orion.grpc.notification.DeviceInfo toGrpcDeviceInfo() {
//                return dev.orion.grpc.notification.DeviceInfo.newBuilder()
//                        .setDeviceId(deviceInfo.getDeviceId())
//                        .setFcmToken(deviceInfo().getFcmToken())
//                        .setDeviceOs(deviceInfo().getDeviceOs())
//                        .setOsVersion(deviceInfo.getDeviceOs())
//                        .setAppVersion(deviceInfo.getAppVersion())
//                        .build();
//        }
}