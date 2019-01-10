package pl.proguski.springstaticresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.net.URI;

@SpringBootApplication
@RestController
public class SpringStaticResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStaticResourcesApplication.class, args);
    }

    @GetMapping(path = "ping")
    String ping() {
        return "pong";
    }

    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                System.out.println("Working Directory = " + System.getProperty("user.dir"));

                URI customFilesUri = new File("src/main/resources/custom-location").toURI();
//                URI customFilesUri = new File("spring-static-resources/src/main/resources/custom-location").toURI();
                registry.addResourceHandler("/custom-files/**").addResourceLocations(customFilesUri.toString());

//                URI rootFiles = new File("src/main/resources/custom-static-file-location").toURI();
                URI rootFiles = new File("C:\\P_Projekty\\codesandbox\\spring-static-resources\\src\\main\\resources\\custom-static-file-location").toURI();
//                URI rootFiles = new File("spring-static-resources/src/main/resources/custom-static-file-location").toURI();
                registry.addResourceHandler("/**").addResourceLocations(rootFiles.toString());
            }
        };
    }


}

