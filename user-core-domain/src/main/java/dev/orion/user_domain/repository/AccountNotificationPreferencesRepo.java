package dev.orion.user_domain.repository;

import dev.orion.core.domain.repository.AbstractRepository;
import dev.orion.user_domain.entity.AccountNotificationPreferences;

import java.util.UUID;

public interface AccountNotificationPreferencesRepo extends AbstractRepository<AccountNotificationPreferences, UUID> {
}
