package dev.orion.user_service_main_application.admin.api;

import dev.orion.commons.model.ApiResponse;
import dev.orion.grpc.auth.private_client.Action;
import dev.orion.track_my_vehicle_auth_client.annotations.PreAuthorizeManager;
import dev.orion.user_service_main_application.admin.request.AdminCreateRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminRegistrationController {

    @PostMapping("new")
    @PreAuthorizeManager(resource = "admin", action = Action.create)
    public ApiResponse<?> create(
            @Validated @RequestBody AdminCreateRequest request,
            BindingResult result
            ) {

        return ApiResponse.success(null);
    }
}
