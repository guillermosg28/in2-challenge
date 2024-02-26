package dev.guillermosg.in2.domain.exception;

/**
 * The type Spacecraft found exception.
 */
public class SpacecraftFoundException extends RuntimeException {

    public static final String ERROR_CODE = "001";
    private static final long serialVersionUID = 1L;

    public SpacecraftFoundException() {
        super();
    }
}
