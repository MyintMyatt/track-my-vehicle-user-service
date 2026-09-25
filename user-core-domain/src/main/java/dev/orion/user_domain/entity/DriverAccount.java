package dev.orion.user_domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "driver_account"
)
@Data
public class DriverAccount extends Account{

    @Column(nullable = false)
    private String nrc;

    @Column(nullable = false)
    private String nrcFront;

    @Column(nullable = false)
    private String nrcBack;

    @Column(nullable = false)
    private String driverLicenseNo;

    @Column(nullable = false)
    private LocalDate licenseValidatedTo;

    @Column(nullable = false)
    private String licenseImgFront;

    @Column(nullable = false)
    private String licenseImgBack;

}

