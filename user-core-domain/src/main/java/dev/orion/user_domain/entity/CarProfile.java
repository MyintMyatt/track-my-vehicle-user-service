package dev.orion.user_domain.entity;

import dev.orion.core.domain.auditor.AuditoryEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "car_profile"
)
@Data
public class CarProfile extends AuditoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String carLicenseNo;

    @Column(nullable = false)
    private String capacity;

    @Column(nullable = false)
    private String model; // car model

    @Column(nullable = false)
    private BigDecimal feesPerUnit;

     

    @ElementCollection
    @CollectionTable(name = "car_images", joinColumns = @JoinColumn(name = "car_id"))
    private List<String> carImages = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate licenseValidatedTo;

    @Column(nullable = false)
    private String licenseImg;
}
