package com.omarapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.omarapi.DataModels.Student;
import com.omarapi.Repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService {
   
    
    private final StudentRepository studentRepository;
    public List <Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
