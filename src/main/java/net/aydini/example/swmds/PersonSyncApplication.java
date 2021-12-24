package net.aydini.example.swmds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class PersonSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonSyncApplication.class, args);
	}

}
