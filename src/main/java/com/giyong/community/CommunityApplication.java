package com.giyong.community;

import com.giyong.community.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableAutoConfiguration
public class CommunityApplication {

	@Autowired
	private static Environment environment;

	@Autowired
	public CommunityApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
//		java -jar SampleApp -Dspring.profiles.active=dev
//		java -jar SampleApp -Dspring.profiles.active=release

		SpringApplication.run(CommunityApplication.class, args);

//		CommunityApplication communityApplication = new CommunityApplication(environment);
//		communityApplication.contextLoads();
	}

//	public void contextLoads() {
//		System.out.println("CommunityApplication.contextLoads");
//		System.out.println("profile: " + environment.getProperty("spring.profiles.active"));
//		System.out.println("username: " + environment.getProperty("spring.datasource.username"));
//	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
}
