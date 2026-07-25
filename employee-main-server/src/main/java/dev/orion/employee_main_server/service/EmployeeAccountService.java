package dev.orion.employee_main_server.service;

import dev.orion.commons.exception.BusinessException;
import dev.orion.core.domain.transaction.constant.TransactionState;
import dev.orion.employee_domain.entity.EmployeeAccount;
import dev.orion.employee_domain.repository.EmployeeAccountRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class EmployeeAccountService {

    private final EmployeeAccountRepo accountRepo;

    // check username is unique or not
    public boolean checkAccountExisted(String username) {
        var employee = accountRepo.findOne(findByUserName(username)).orElse(null);
        return employee != null;
    }

    public EmployeeAccount findByUsername(String username){
        return accountRepo.findOne(findByUserName(username)).orElseThrow(() -> new BusinessException("Employee account does not existed"));
    }

    private Function<CriteriaBuilder, CriteriaQuery<EmployeeAccount>> findByUserName(String username) {
        return cb -> {
            var cq = cb.createQuery(EmployeeAccount.class);
            var root = cq.from(EmployeeAccount.class);
            cq.select(root);

            cq.where(
                    cb.and(
                            cb.equal(root.get("username"), username),
                            cb.notEqual(root.get("transactionState"), TransactionState.FAIL),
                            cb.isFalse(root.get("auditInfo").get("deleted"))
                    )
            );
            return cq;
        };
    }
}
