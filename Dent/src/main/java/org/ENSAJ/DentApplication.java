package org.ENSAJ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.ENSAJ")
public class DentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentApplication.class, args);
	}

}
