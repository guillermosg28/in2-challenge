package dev.guillermosg.in2.infrastructure.adapters.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageSizeConfig {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int defaultPageSize;

    public int getDefaultPageSize() {
        return defaultPageSize;
    }
}
