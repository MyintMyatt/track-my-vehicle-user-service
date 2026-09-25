package dev.orion.user_domain.entity;

import dev.orion.core.domain.auditor.AuditoryEntity;
import dev.orion.user_domain.embedded.UserName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "account_notification_preferences")
@Data
public class AccountNotificationPreferences extends AuditoryEntity {

    @Id
    @Column(name = "account_id")
    private UUID accountId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private UserName userName;

    private boolean emailEnable = true;
    private boolean fcmEnable = false;
    private boolean smsEnable = false;

    public AccountNotificationPreferences defaultEntity(Account account){
        var entity = new AccountNotificationPreferences();
        entity.setAccount(account);
        entity.setUserName(account.getUserName());
        return entity;
    }

}
