package dev.guillermosg.in2.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The Spacecraft entity.
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "spacecrafts")
public class SpacecraftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "production_type")
    @NotNull
    private String productionType;

    @Column(name = "model")
    @NotNull
    private String model;

    @Column(name = "manufacturer")
    @NotNull
    private String manufacturer;

    @Column(name = "passengers")
    @NotNull
    private Integer passengers;

}
