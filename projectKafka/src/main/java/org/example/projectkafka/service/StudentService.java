package org.example.projectkafka.service;

import org.example.projectkafka.model.Student;
import org.example.projectkafka.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findByParentId(String parentId) {
        return studentRepository.findByParentId(parentId);
    }
}

