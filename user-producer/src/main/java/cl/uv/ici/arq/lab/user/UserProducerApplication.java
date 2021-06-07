package cl.uv.ici.arq.lab.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ConfigurationProperties
@PropertySources({
        @PropertySource("classpath:rabbitmq.properties") 
})
public class UserProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProducerApplication.class, args);
	}
}
