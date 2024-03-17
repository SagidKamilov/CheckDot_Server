package com.cityfine.checkdot_server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cityfine.checkdot_server.dto.ConverterDTO;

@Configuration
public class AppConfig {

    @Bean
    public ConverterDTO converterDTO() {
        return new ConverterDTO();
    }
}