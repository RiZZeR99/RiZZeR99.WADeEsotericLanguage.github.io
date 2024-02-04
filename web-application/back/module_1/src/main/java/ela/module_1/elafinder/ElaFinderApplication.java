package ela.module_1.elafinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class ElaFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElaFinderApplication.class, args);
    }

}
