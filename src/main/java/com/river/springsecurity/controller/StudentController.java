package com.river.springsecurity.controller;

import com.river.springsecurity.model.Student;
import com.river.springsecurity.service.StudentService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping(path = "/students")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Student> getStudents() {
        return service.getStudents();
    }

    @GetMapping(path = "/students/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Student getStudent(@PathVariable(value = "id") Integer id) {
        return service.getStudent(id);
    }

    @PostMapping(path = "/students")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Student addStudents(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @PatchMapping(path = "/students/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateStudents(@PathVariable(value = "id") Integer id, @RequestBody Student student) {
        service.updateStudent(id, student);
    }

    @DeleteMapping(path = "/students/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStudents(@PathVariable(value = "id") Integer id) {
        service.deleteStudent(id);
    }

    @GetMapping(path = "/setup")
    public void setup() {
        List<Student> students = List.of(
                new Student(1, "Yasuo", 21),
                new Student(2, "Darius", 22),
                new Student(3, "Zed", 23),
                new Student(4, "Zin", 24),
                new Student(5, "Ekko", 25),
                new Student(6, "Zoe", 26),
                new Student(7, "Nautilus", 27)
        );
        service.addStudents(students);
    }

}
