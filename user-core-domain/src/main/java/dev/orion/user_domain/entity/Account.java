package dev.orion.user_domain.entity;

import dev.orion.commons.constant.Gender;
import dev.orion.core.domain.auditor.AuditoryEntity;
import dev.orion.core.domain.transaction.constant.TransactionState;
import dev.orion.user_domain.embedded.UserName;
import dev.orion.user_domain.utils.JpaStringToListConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "account",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_account_username", columnNames = "username"),
        })
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Account  extends AuditoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private UserName userName;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Convert(converter = JpaStringToListConverter.class)
    private List<String> phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionState transactionState = TransactionState.PENDING;
}
