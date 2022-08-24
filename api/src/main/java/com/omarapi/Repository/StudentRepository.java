package com.omarapi.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.omarapi.DataModels.Student;

public interface StudentRepository extends MongoRepository<Student,String> {
    
    Optional <Student> findStudentByEmail(String email);
}
