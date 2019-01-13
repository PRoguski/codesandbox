package pl.proguski.springmocks.flightplan;

import org.springframework.stereotype.Component;

@Component
public class FlightPlanEditor {
    private FlightPlan flightPlan;

    public FlightPlanEditor(FlightPlan flightPlan) {
        this.flightPlan = flightPlan;
    }

    public String test() {
        return flightPlan.getPlan("342") + " dodifyfy ";
    }
}
