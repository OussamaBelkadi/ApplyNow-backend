package example.brief.MyRh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "example.brief.MyRh.entities")
@SpringBootApplication
public class RecruterProApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruterProApplication.class, args);
    }

}
