package pl.com.bottega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CinemaCApp {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CinemaCApp.class, args);
    }
}
