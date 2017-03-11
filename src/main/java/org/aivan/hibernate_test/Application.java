package org.aivan.hibernate_test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.aivan.hibernate_test.db.ApiRequestService;
import org.aivan.hibernate_test.db.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "org.aivan.*" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	ApiRequestService apiService;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

			System.out.println("api service is: " + apiService);
			assert (apiService != null);
			
			apiService.create(new ApiRequest(new Date()));

			System.out.println("START Existing records:");
			List<ApiRequest> all = apiService.findAll();
			all.forEach(a -> System.out.println(a.getId() + " " + a.getRequestTime()));
			System.out.println("END: Existing records:");
		};
	}

}