package com.gakopyan.cawa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Project: cawa
 * Created by George Akopyan on 26.10.2017
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AppProperties {
    @Value("${server.port}")
    public String serverPort;


}
