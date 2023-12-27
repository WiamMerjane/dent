package org.ENSAJ.controller;

import org.ENSAJ.entites.PW;
import org.ENSAJ.services.PWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pws")
@CrossOrigin(origins = "http://localhost:3000")
public class PWController {

    private final PWService pwService;

    @Autowired
    public PWController(PWService pwService) {
        this.pwService = pwService;
    }

    @PostMapping
    public PW createPW(@RequestBody PW pw) {
        return pwService.create(pw);
    }

    @PutMapping("/{id}")
    public PW updatePW(@PathVariable int id, @RequestBody PW pw) {
        pw.setId(id);
        return pwService.update(pw);
    }

    @GetMapping("/{id}")
    public PW getPWById(@PathVariable int id) {
        return pwService.findById(id);
    }

    @GetMapping
    public List<PW> getAllPWs() {
        return pwService.findAll();
    }

    @DeleteMapping("/{id}")
    public boolean deletePW(@PathVariable int id) {
        PW pw = pwService.findById(id);
        if (pw != null) {
            return pwService.delete(pw);
        }
        return false;
    }
}
