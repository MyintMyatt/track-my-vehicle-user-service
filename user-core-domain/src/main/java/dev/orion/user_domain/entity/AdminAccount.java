package dev.orion.user_domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "admin_account",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_admin_username", columnNames = "username"),
                @UniqueConstraint(name = "uk_admin_employee_id", columnNames = "employee_id"),
                @UniqueConstraint(name = "uk_admin_phone", columnNames = "phone"),
                @UniqueConstraint(name = "uk_admin_email", columnNames = "email")
        })
@Data
public class AdminAccount extends Account {
    /**
     * Employee ID from company's HR/Employee Management System.
     */
    @Column(name = "employee_id", nullable = false, length = 30)
    private String employeeId;

    @Column(nullable = false, length = 100)
    private String email;

    private String profileUrl;

    @ElementCollection
    @CollectionTable(name = "employee_image", joinColumns = @JoinColumn(name = "employee_id"))
    private List<String> images = new ArrayList<>();
}
