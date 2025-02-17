package com.river.springsecurity.service;

import com.river.springsecurity.model.Student;
import com.river.springsecurity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public Student getStudent(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        return repository.save(student);
    }

    public Student updateStudent(Integer id, Student student) {
        student.setId(id);
        return repository.save(student);
    }

    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }

    public void addStudents(List<Student> students) {
        repository.saveAll(students);
    }
}
