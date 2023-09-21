package com.giyong.community;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("storage")
@Data
public class StorageProperties {

//    private String location = "src/main/resources/static/img/";

    @Value("${file.upload.directory}")
    private String location;
}
