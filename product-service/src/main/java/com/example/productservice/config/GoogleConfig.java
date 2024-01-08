package com.example.productservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GoogleConfig {
    @Bean
    public GoogleCredentials googleCredentials() throws IOException {


        GoogleCredentials googleCredentials = GoogleCredentials.newBuilder().build();
        return googleCredentials;
    }

}
