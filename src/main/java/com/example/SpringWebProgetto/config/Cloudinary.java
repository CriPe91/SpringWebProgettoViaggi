package com.example.SpringWebProgetto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Cloudinary {
    @Bean
    public com.cloudinary.Cloudinary uploader(){
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "dwsgqdsfn");
        config.put("api_key", "546196251472678");
        config.put("api_secret", "5UQDUBYWI3AqzDlzkecCb4Ey4LY");
        return new com.cloudinary.Cloudinary(config);
    }
}
