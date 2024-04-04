package org.mgb.customerservice;

import org.mgb.customerservice.entities.Customer;
import org.mgb.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin("/**")
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
            customerRepository.saveAll(List.of(
                    Customer.builder().name("Mohamed").email("med@gmail.com").build(),
                    Customer.builder().name("Hassan").email("hasan@gmail.com").build(),
                    Customer.builder().name("IMane").email("imane@gmail.com").build()
            ));
            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
