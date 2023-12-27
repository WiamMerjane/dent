package org.ENSAJ.controller;

import org.ENSAJ.entites.Tooth;
import org.ENSAJ.services.ToothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teeth")
@CrossOrigin(origins = "http://localhost:3000")
public class ToothController {

    private final ToothService toothService;

    @Autowired
    public ToothController(ToothService toothService) {
        this.toothService = toothService;
    }

    @PostMapping
    public Tooth createTooth(@RequestBody Tooth tooth) {
        return toothService.create(tooth);
    }

    @PutMapping("/{id}")
    public Tooth updateTooth(@PathVariable int id, @RequestBody Tooth tooth) {
        tooth.setId(id);
        return toothService.update(tooth);
    }

    @GetMapping("/{id}")
    public Tooth getToothById(@PathVariable int id) {
        return toothService.findById(id);
    }

    @GetMapping
    public List<Tooth> getAllTeeth() {
        return toothService.findAll();
    }

    @DeleteMapping("/{id}")
    public boolean deleteTooth(@PathVariable int id) {
        Tooth tooth = toothService.findById(id);
        if (tooth != null) {
            return toothService.delete(tooth);
        }
        return false;
    }
}
