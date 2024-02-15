package com.amigoscode;


import com.amigoscode.customer.Customer;
import com.amigoscode.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for(String bd: beanDefinitionNames) {
//            System.out.println(bd);
//        }
    }
    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            Customer alex = new Customer("Alex", "alex@mail.com", 21);
            Customer jamila = new Customer("Jamila", "jamila@mail.com", 21);
            List<Customer> customers = List.of(alex, jamila);
           // customerRepository.saveAll(customers);
        };
    }



//    record Foo(String name) {};


}




