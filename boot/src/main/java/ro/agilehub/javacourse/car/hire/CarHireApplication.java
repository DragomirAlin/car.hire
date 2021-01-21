package ro.agilehub.javacourse.car.hire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@ComponentScan(basePackages = "ro.agilehub.javacourse.car.hire")
@SpringBootApplication
@EnableMongoAuditing
public class CarHireApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CarHireApplication.class, args);
    }
}
