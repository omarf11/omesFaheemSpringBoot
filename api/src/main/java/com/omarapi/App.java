package com.omarapi;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.omarapi.DataModels.Address;
import com.omarapi.DataModels.Gender;
import com.omarapi.DataModels.Student;
import com.omarapi.Repository.StudentRepository;

@SpringBootApplication
public class App {
    private String email;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner runner(
            StudentRepository repository, MongoTemplate mongoTemplate) {

        return args -> {
            Address addy = new Address(
                    "USA",
                    "Boken",
                    "07030");

            email = "omes@test12.com";
            Student student = new Student(
                    "omar",
                    "faheem",
                    email,
                    Gender.MALE,
                    addy,
                    List.of("compSci", "maths"),
                    BigDecimal.TEN,
                    LocalDateTime.now());

            // usingMongoTemplateAndQuery(repository, mongoTemplate, student);

            //much easier way to query
            repository.findStudentByEmail(email)
                    .ifPresentOrElse(s -> {
                        System.out.println(s  + " already exists");
                    }, () -> {
                        System.out.println("inserting Student " + student);
                        repository.insert(student);
                    });

        };
    }

    // private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate,
    //         Student student) {
    //     Query query = new Query();
    //     query.addCriteria(Criteria.where("email").is(email));

    //     List<Student> students = mongoTemplate.find(query, Student.class);

    //     if (students.size() > 1) {
    //         throw new IllegalStateException("Found many students with email: " + email);
    //     }
    //     if (students.isEmpty()) {
    //         System.out.println("inserting Student " + student);
    //         repository.insert(student);
    //     } else {
    //         System.out.println(student + " already exists");
    //     }
    // }

}