package dev.orion.user_domain.repository;

import dev.orion.core.domain.repository.AbstractRepository;
import dev.orion.user_domain.entity.EmployeeAccount;

import java.util.UUID;

public interface EmployeeAccountRepo extends AbstractRepository<EmployeeAccount, UUID> {
}
