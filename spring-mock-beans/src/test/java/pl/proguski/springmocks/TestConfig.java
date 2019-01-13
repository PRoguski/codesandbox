package pl.proguski.springmocks;

import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.proguski.springmocks.flightplan.FlightPlan;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

//    @Bean
//    @Primary
//    FlightPlan flightPlan() {
//        return mock(FlightPlan.class);
//    }

}
