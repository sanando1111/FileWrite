package com.dev.boot;

import com.dev.boot.entity.Employee;
import com.dev.boot.repository.EmployeeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWithDataApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringBootWithDataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWithDataApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeRepository repository) {
        return (args) -> {

            // save a couple of employees
            repository.save(new Employee("Jack", "Bauer", 10000L));
            repository.save(new Employee("Chloe", "O'Brian", 10000L));
            repository.save(new Employee("Kim", "Bauer", 10000L));
            repository.save(new Employee("David", "Palmer", 10000L));
            repository.save(new Employee("Michelle", "Dessler", 1000L));

            // fetch all employees
            log.info("Employees found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(allEmployee -> log.info(allEmployee.toString()));
            log.info("");

            // fetch an individual employee by ID
            Employee employeeById = repository.findOne(1L);
            log.info("Employee found with findOne(1L):");
            log.info("--------------------------------");
            log.info(employeeById.toString());
            log.info("");

            // fetch employee by last name
            log.info("Employee found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(employeeByLastName -> log.info(employeeByLastName.toString()));
            log.info("");

            // write data to multiple files
            log.info("Writing to file Employees found with findAll():");
            log.info("-------------------------------");
            Path outputFile = Paths.get("C:/Users/prati/Desktop/output.txt");
            // For full control use custom ThreadFactory and pass it as the second parameter
            ExecutorService executor = Executors.newFixedThreadPool(2);
            log.info("Perform cleanup tasks");
            log.info("-------------------------------");
            Files.deleteIfExists(outputFile);
            for (int num = 0; num <= 10; num++) {
                CompletableFuture.runAsync(() -> {
                    log.info("Executing async thread");
                    try {
                        Files.write(outputFile,
                                String.format("thread %s -> data %s \n\n", Thread.currentThread().getName(),
                                        repository.findAll().toString()).getBytes(),
                                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } catch (IOException ioe) {
                        log.error(ioe.getMessage());
                    }
                    log.info("Execution completed");
                }, executor);

            }
        };
    }

}
