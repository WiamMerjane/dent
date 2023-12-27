package org.ENSAJ.controller;

import org.ENSAJ.entites.Admin;
import org.ENSAJ.entites.User;
import org.ENSAJ.repository.AdminRepository;
import org.ENSAJ.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:3000") // Add the origin of your React app

public class AdminController {

    private final AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.create(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
        admin.setId(id);
        return adminService.update(admin);
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable int id) {
        return adminService.findById(id);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.findAll();
    }

    @DeleteMapping("/{id}")
    public boolean deleteAdmin(@PathVariable int id) {
        Admin admin = adminService.findById(id);
        if (admin != null) {
            return adminService.delete(admin);
        }
        return false;
    }


//    @PostMapping
//    public ResponseEntity<String> login(@RequestBody Admin admin) {
//        // Récupérer les informations d'identification depuis la demande
//        String userName = admin.getUserName();
//        String password = admin.getPassword();
//
//        // Authentifier
//        if (adminService.authenticate(userName, password)) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String username = user.getUserName();
        String password = user.getPassword();

        Admin admin = adminService.login(username, password);

        if (admin != null) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/login/{userName}")

    public ResponseEntity<Admin> getAdminByUsername(@PathVariable String userName) {
        Admin admin = adminRepository.findByUserName(userName);

        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
