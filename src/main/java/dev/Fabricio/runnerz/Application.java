package dev.Fabricio.runnerz;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.Fabricio.runnerz.run.Location;
import dev.Fabricio.runnerz.run.Run;

@SpringBootApplication
public class Application {

	private final static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Integer id = 1;
            String title = "Morning Run";
            LocalDateTime startedOn = LocalDateTime.now().minusHours(1);
            LocalDateTime finishedOn = LocalDateTime.now();
            Integer miles = 5;

            Run run = new Run(id, title, startedOn, finishedOn, miles,Location.OUTDOOR);
			log.info("Run :"+run);
		};

	}

}
