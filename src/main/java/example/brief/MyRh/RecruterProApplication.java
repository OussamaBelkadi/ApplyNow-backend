package example.brief.MyRh;

import example.brief.MyRh.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "example.brief.MyRh.entities")
@SpringBootApplication
public class RecruterProApplication implements CommandLineRunner {
    @Autowired
    private SpecialityService specialityService;

    public static void main(String[] args) {
        SpringApplication.run(RecruterProApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        specialityService.addSpeciality();
    }
}
