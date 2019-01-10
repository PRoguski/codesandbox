package pl.proguski.springstaticresources;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void ping() {
        webClient.get().uri("/ping").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo("pong");
    }

    @Test
    public void getStaticFileFromCustomLocation_ShouldReturnFileContent() {
        webClient.get().uri("/custom-files/file.txt").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo("file");
    }

    @Test
    public void getStaticFilesFromCustomLocation_ShouldReturnTestate() {
        webClient.get().uri("/test/test.html").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo("test");
    }

}

