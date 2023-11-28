package bootcamp.com.br.matera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MateraApplication {

	public static void main(String[] args) {
		SpringApplication.run(MateraApplication.class, args);
	}

}
