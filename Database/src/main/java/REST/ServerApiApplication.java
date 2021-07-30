package REST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServerApiApplication {

	public static void main(String[] args) {
		try {
//			DatabaseConnection.setUpDatabase();
			SpringApplication.run(ServerApiApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
