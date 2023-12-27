package org.ENSAJ.controller;

import org.ENSAJ.entites.Admin;
import org.ENSAJ.entites.Student;
import org.ENSAJ.entites.User;
import org.ENSAJ.repository.AdminRepository;
import org.ENSAJ.repository.StudentRepository;
import org.ENSAJ.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    private StudentRepository studentRepository;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        return studentService.update(student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.findById(id);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable int id) {
        Student student = studentService.findById(id);
        if (student != null) {
            return studentService.delete(student);
        }
        return false;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String username = user.getUserName();
        String password = user.getPassword();

        Student student = studentService.login(username, password);

        if (student != null) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/login/{userName}")

    public ResponseEntity<Student> getAdminByUsername(@PathVariable String userName) {
        Student student = studentRepository.findByUserName(userName);

        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}