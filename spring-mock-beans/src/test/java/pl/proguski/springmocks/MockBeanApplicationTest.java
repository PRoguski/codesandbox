package pl.proguski.springmocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.proguski.springmocks.flightplan.FlightPlan;
import pl.proguski.springmocks.flightplan.FlightPlanEditor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MockBeanApplicationTest {

//    @Autowired
//    FlightPlan flightPlan;//= Mockito.mock(FlightPlan.class);

//    @Before
//    public void setUp() throws Exception {
//        given(this.flightPlan.getPlan(any())).willReturn("mockFlightPlan");
//    }


    @MockBean
    FlightPlan flightPlan;

    @Autowired
    FlightPlanEditor flightPlanEditor;

    @Test
    public void tlrolrotest() {
        given(flightPlan.getPlan(any())).willReturn("mockFlightPlan");

        String plan = flightPlan.getPlan("23234");

        System.out.println("Mock in test: " + plan);

        String test = flightPlanEditor.test();

        System.out.println("FlightPlanEditor teest : " + test);
    }

}