package dev.guillermosg.in2.infrastructure.adapters.output.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NegativeIdLoggingAspect {

    @Before("execution(* dev.guillermosg.in2.domain.service.SpacecraftService._getSpacecraftById(Integer)) && args(id)")
    public void logNegativeIdRequest(JoinPoint joinPoint, Integer id) {
        if (id < 0) {
            System.out.println("Se solicitó una nave con ID negativo: " + id);
        }
    }
}
