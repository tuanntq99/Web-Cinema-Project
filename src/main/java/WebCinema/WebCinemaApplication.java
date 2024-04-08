package WebCinema;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Web-Cinema API",
        version = "1.0",
        description = "API Controller",
//        termsOfService = "run",
        contact = @Contact(
                name = "Tuan Tran",
                email = "tuanhd131@gmail.com"
        )
//        license = @License(
//                name = "license",
//                url = "run"
//        )
))
public class WebCinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCinemaApplication.class, args);
    }

    @Bean
    public ModelMapper modalMapper() {
        return new ModelMapper();
    }
}
