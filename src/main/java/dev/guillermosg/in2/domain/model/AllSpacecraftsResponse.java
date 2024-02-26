package dev.guillermosg.in2.domain.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * The model AllSpacecraftsResponse.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllSpacecraftsResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Spacecraft> spacecrafts;
    private Integer page;
    private Integer size;
    private Integer totalElements;
    private Integer totalPages;
    private Boolean last;
    private Boolean first;
}
