package dev.orion.user_domain.entity;

import dev.orion.commons.constant.Gender;
import dev.orion.core.domain.auditor.AuditoryEntity;
import dev.orion.core.domain.transaction.constant.TransactionState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "employee_account",
        uniqueConstraints = {
        @UniqueConstraint(name = "uk_employee_username", columnNames = "username"),
        @UniqueConstraint(name = "uk_employee_employee_id", columnNames = "employee_id"),
        @UniqueConstraint(name = "uk_employee_phone", columnNames = "phone"),
        @UniqueConstraint(name = "uk_employee_email", columnNames = "email")
})
@Data
public class EmployeeAccount extends AuditoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String username;

    /**
     * Employee ID from company's HR/Employee Management System.
     */
    @Column(name = "employee_id", nullable = false, length = 30)
    private String employeeId;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionState transactionState = TransactionState.PENDING;
}
