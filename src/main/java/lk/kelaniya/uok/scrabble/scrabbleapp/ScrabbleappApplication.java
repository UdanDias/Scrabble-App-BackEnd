package lk.kelaniya.uok.scrabble.scrabbleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "lk.kelaniya.uok.scrabble.scrabbleapp.entity")
public class ScrabbleappApplication {

	public static void main(String[] args) {

		SpringApplication.run(ScrabbleappApplication.class, args);
	}

}
