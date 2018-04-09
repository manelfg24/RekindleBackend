package com.pes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Per afegir al gradle build
 * compile('org.springframework.boot:spring-boot-starter-security')
 */

@SpringBootApplication
public class RekindleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RekindleApplication.class, args);
	}
}
