package dev.guillermosg.in2.domain.model;

import lombok.*;

import java.io.Serializable;

/**
 * The model Spacecraft.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spacecraft implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private String name;
        private String productionType;
        private String model;
        private String manufacturer;
        private Integer passengers;
}
