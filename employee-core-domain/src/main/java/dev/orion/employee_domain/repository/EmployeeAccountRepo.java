package dev.orion.domain.repository;

import dev.orion.core.domain.repository.AbstractRepository;
import dev.orion.domain.entity.EmployeeAccount;

import java.util.UUID;

public interface EmployeeAccountRepo extends AbstractRepository<EmployeeAccount, UUID> {
}
