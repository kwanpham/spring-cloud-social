package quan.dev.galaxyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class GalaxyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyServiceApplication.class, args);
	}

}
