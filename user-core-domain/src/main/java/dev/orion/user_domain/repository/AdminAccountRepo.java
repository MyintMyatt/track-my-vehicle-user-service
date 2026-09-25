package dev.orion.user_domain.repository;

import dev.orion.core.domain.repository.AbstractRepository;
import dev.orion.user_domain.entity.AdminAccount;

import java.util.UUID;

public interface AdminAccountRepo extends AbstractRepository<AdminAccount, UUID> {
}
