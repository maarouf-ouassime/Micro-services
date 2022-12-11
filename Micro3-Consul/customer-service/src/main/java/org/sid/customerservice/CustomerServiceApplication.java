package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.saveAll(
					List.of(
							Customer.builder().name("aaa").email("aaa@gmail.com").build(),
							Customer.builder().name("bbb").email("bbb@gmail.com").build(),
							Customer.builder().name("ccc").email("ccc@gmail.com").build(),
							Customer.builder().name("ddd").email("ddd@gmail.com").build()

					));
			customerRepository.findAll().forEach(System.out::println);
		};
	}
}
