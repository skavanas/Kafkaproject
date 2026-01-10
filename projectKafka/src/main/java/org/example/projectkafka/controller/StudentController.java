package org.example.projectkafka.controller;

import org.example.projectkafka.model.Student;
import org.example.projectkafka.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/parent/{parentId}")
    public List<Student> getStudentsByParent(@PathVariable String parentId) {
        return studentService.findByParentId(parentId);
    }
}

