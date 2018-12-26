package plaintext

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.jboss.resteasy.client.jaxrs.ResteasyClient
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget
import org.junit.Rule
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.TimeUnit

import static com.github.tomakehurst.wiremock.client.WireMock.*

class PlainTextClient extends Specification {

    private static int PORT = 8080

    @Rule
    WireMockRule wireMockRule = new WireMockRule(PORT)

    @Shared
    PingEndpoint endpoint

    void setup() {
        ResteasyClient client = new ResteasyClientBuilder()
                .socketTimeout(200, TimeUnit.MICROSECONDS)
                .establishConnectionTimeout(200, TimeUnit.MICROSECONDS)
                .build()
        ResteasyWebTarget target = client.target("http://localhost:$PORT")
        endpoint = target.proxy(PingEndpoint.class)
    }

    void cleanup() {

    }

    def "GET 200"() {
        given:
        stubFor(get(urlEqualTo("/api/ping"))
                .withHeader("Accept", equalTo("text/plain"))
                .willReturn(aResponse()
                .withFixedDelay(1000)
                .withStatus(200)
                .withHeader("Content-Type", "text/plain")
                .withBody("pong")))
        when:
        def ping = endpoint.ping()
        then:
        ping == 'pong'
    }

    def "GET redirect 302"() {
        given:

        stubFor(
                get(urlEqualTo("/api/ping"))
                        .willReturn(aResponse()
                        .withStatus(302)
                        .withHeader("Content-Type", "text/plain")
                        .withHeader("Location", "http://localhost:8080/api/301")
                        .withBody("pong")))

        stubFor(get(urlEqualTo("/api/301"))
                .withHeader("Accept", equalTo("text/plain"))
                .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "text/plain")
                .withBody("pong")))

        when:
        def ping = endpoint.ping()
        then:
        ping == 'pong'
    }

}
