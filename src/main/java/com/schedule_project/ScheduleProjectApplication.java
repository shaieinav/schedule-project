package com.schedule_project;

import com.schedule_project.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class ScheduleProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(ScheduleProjectApplication.class, args);
    }
}