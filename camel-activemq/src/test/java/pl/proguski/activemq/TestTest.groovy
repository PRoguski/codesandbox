package pl.proguski.activemq

import org.apache.activemq.broker.BrokerService
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.impl.DefaultCamelContext
import spock.lang.Specification

class TestTest extends Specification {
    void setup() {
        BrokerService broker = new BrokerService()
        broker.setBrokerName("test")
        broker.addConnector("tcp://localhost:61616")
        broker.start()
    }

    void cleanup() {
    }

    def "scsc"() {
        given:
        CamelContext context = new DefaultCamelContext()
        context.addRoutes()

        context.start()
        when:
        1 + 1
        then:
        true
    }


    class RecordProcessor implements Processor {

        void process(Exchange msg) {
            log.trace("Processing msg {}", msg)
            Map<String, Object> record = msg.getIn().getBody(Map.class)
            log.info("Processing record {}", record)
            // Do something useful with this record.
        }
    }
}
