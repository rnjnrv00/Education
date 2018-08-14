package aak.profile.read.education.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"aak.profile.read.education"})
public class EducationDataReadServer {

	public static void main(String[] args) {
		SpringApplication.run(EducationDataReadServer.class, args);
	}

}
