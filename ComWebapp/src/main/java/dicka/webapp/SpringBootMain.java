package dicka.webapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"dicka.repository"})
@EntityScan(basePackages = {"dicka.model"})
@ComponentScan(basePackages = {"dicka.model",
        "dicka.repository",
        "dicka.dao",
        "dicka.config",
        "dicka.services",
        "dicka.webapp.*"})
public class SpringBootMain {

    public static void main(String[] args){
        SpringApplication.run(SpringBootMain.class, args);
    }
}
