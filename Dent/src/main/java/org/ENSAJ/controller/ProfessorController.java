package org.ENSAJ.controller;

import org.ENSAJ.entites.Admin;
import org.ENSAJ.entites.Professor;
import org.ENSAJ.entites.User;
import org.ENSAJ.repository.ProfessorRepository;
import org.ENSAJ.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorService.create(professor);
    }

    @PutMapping("/{id}")
    public Professor updateProfessor(@PathVariable int id, @RequestBody Professor professor) {
        professor.setId(id);
        return professorService.update(professor);
    }

    @GetMapping("/{id}")
    public Professor getProfessorById(@PathVariable int id) {
        return professorService.findById(id);
    }

    @GetMapping
    public List<Professor> getAllProfessors() {
        return professorService.findAll();
    }

    @DeleteMapping("/{id}")
    public boolean deleteProfessor(@PathVariable int id) {
        Professor professor = professorService.findById(id);
        if (professor != null) {
            return professorService.delete(professor);
        }
        return false;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String username = user.getUserName();
        String password = user.getPassword();

        Professor professor = professorService.login(username, password);

        if (professor != null) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/login/{userName}")

    public ResponseEntity<Professor> getProfessorByUsername(@PathVariable String userName) {
        Professor professor = professorRepository.findByUserName(userName);

        if (professor != null) {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
