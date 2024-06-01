package dev.manyroads.angularbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RunAngularBE {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(RunAngularBE.class);

        // Checking the MySQL DB connection with the DOCKER MySQL container
        String mysqlUri = ctx.getEnvironment().getProperty("spring.datasource.url");

        System.out.println(("Connected to MySQL: " + mysqlUri));
    }
}
