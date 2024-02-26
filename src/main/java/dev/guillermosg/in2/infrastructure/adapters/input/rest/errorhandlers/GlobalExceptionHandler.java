package dev.guillermosg.in2.infrastructure.adapters.input.rest.errorhandlers;

import dev.guillermosg.in2.application.ports.dto.ErrorDTO;
import dev.guillermosg.in2.domain.exception.SpacecraftFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

/**
 * The type Global exception handler.
 */

@ControllerAdvice

public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    /**
     * Handle unavailable balance exception response entity.
     *
     * @param locale the locale
     * @return the response entity
     */
    @ExceptionHandler(SpacecraftFoundException.class)
    public ResponseEntity<Object> handleSpacecraftFoundException(Locale locale) {
        String errorMessage = messageSource.getMessage("message.exception.spacecraftnotfound", null, locale);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder().codResponse(SpacecraftFoundException.ERROR_CODE)
                        .desResponse(errorMessage).build());
    }


    /**
     * Handle method argument not valid response entity.
     *
     * @return the response entity
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(Locale locale) {
        String errorMessage = messageSource.getMessage("message.exception.missingparameter", null, locale);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder().codResponse("400")
                        .desResponse(errorMessage).build());
    }

}
