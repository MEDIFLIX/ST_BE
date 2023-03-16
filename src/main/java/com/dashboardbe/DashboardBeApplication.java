package com.dashboardbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy // for AOP
@SpringBootApplication
public class DashboardBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardBeApplication.class, args);
    }

}
