package com.giyong.community;

import com.giyong.community.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//public class CommunityApplication implements CommandLineRunner {
public class CommunityApplication {
//	@Autowired
//	EntityManagerFactory emf;

	public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(CommunityApplication.class);
//		app.setWebApplicationType(WebApplicationType.NONE);
//		app.run(args);
		SpringApplication.run(CommunityApplication.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
//	@Override
//	public void run(String... args) throws Exception {
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
//
//		Member member = new Member();
//		member.setId("giyong@test.com");
//		member.setPassword("1111");
//		member.setNickname("giyong");
//		member.setCreatedAt(new Date());
//		member.setUpdatedAt(new Date());
//
//		tx.begin();
//		em.persist(member);
//		tx.commit();
//
//		em.find(Member.class, "giyong@test.com");
//
//		tx.begin();
//		em.remove(member);
//		tx.commit();
//	}
}
