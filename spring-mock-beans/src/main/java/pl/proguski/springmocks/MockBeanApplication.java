package pl.proguski.springmocks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.proguski.springmocks.flightplan.FlightPlan;

@SpringBootApplication
public class MockBeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockBeanApplication.class, args);
    }


//    @Bean
//    CommandLineRunner runNew(FlightPlan flightPlan) {
//        return args -> {
//            String test = flightPlan.getPlan("test");
//            System.out.println("FlightPlanClient on startup: " + test);
//        };
//    }
}
