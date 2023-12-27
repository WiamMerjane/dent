package org.ENSAJ.controller;

import org.ENSAJ.entites.StudentPW;
import org.ENSAJ.services.StudentPWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/pws")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentPWController {

    private final StudentPWService studentPWService;

    @Autowired
    public StudentPWController(StudentPWService studentPWService) {
        this.studentPWService = studentPWService;
    }

    @GetMapping("/{id}")
    public StudentPW getStudentPWById(@PathVariable int id) {
        return studentPWService.findById(id);
    }

    @GetMapping
    public List<StudentPW> getAllStudentPWs() {
        return studentPWService.findAll();
    }

    @PostMapping
    public StudentPW createStudentPW(@RequestBody StudentPW studentPW) {
        return studentPWService.create(studentPW);
    }

//    @PutMapping("/{id}")
//    public StudentPW updateStudentPW(@PathVariable int id, @RequestBody StudentPW studentPW) {
//        studentPW.setId(id);
//        return studentPWService.update(studentPW);
//    }

    @DeleteMapping("/{id}")
    public boolean deleteStudentPW(@PathVariable int id) {
        StudentPW studentPW = studentPWService.findById(id);
        if (studentPW != null) {
            return studentPWService.delete(studentPW);
        }
        return false;
    }

    // Ajoutez d'autres méthodes si nécessaire

}
