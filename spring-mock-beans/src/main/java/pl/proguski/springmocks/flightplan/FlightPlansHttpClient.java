package pl.proguski.springmocks.flightplan;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FlightPlansHttpClient implements FlightPlan {

    public FlightPlansHttpClient() {
        System.out.println("%%%%%%%%%%%$$$$$$$$$$$$$$$$");
    }

    public String getPlan(String date) {
        return "FakeFlightPlan";
    }

}
