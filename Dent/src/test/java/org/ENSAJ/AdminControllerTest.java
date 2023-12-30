package org.ENSAJ;

import org.ENSAJ.controller.AdminController;
import org.ENSAJ.entites.Admin;
import org.ENSAJ.entites.User;
import org.ENSAJ.repository.AdminRepository;
import org.ENSAJ.services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAdmin() {
        Admin adminToCreate = new Admin(); // Initialize with required data
        when(adminService.create(adminToCreate)).thenReturn(adminToCreate);

        Admin createdAdmin = adminController.createAdmin(adminToCreate);

        assertEquals(adminToCreate, createdAdmin);
        verify(adminService, times(1)).create(adminToCreate);
    }

    @Test
    public void testUpdateAdmin() {
        int adminId = 1;
        Admin adminToUpdate = new Admin(); // Initialize with required data
        when(adminService.update(adminToUpdate)).thenReturn(adminToUpdate);

        Admin updatedAdmin = adminController.updateAdmin(adminId, adminToUpdate);

        assertEquals(adminToUpdate, updatedAdmin);
        verify(adminService, times(1)).update(adminToUpdate);
    }

    @Test
    public void testGetAdminById() {
        int adminId = 1;
        Admin expectedAdmin = new Admin(); // Initialize with required data
        when(adminService.findById(adminId)).thenReturn(expectedAdmin);

        Admin retrievedAdmin = adminController.getAdminById(adminId);

        assertEquals(expectedAdmin, retrievedAdmin);
        verify(adminService, times(1)).findById(adminId);
    }

    @Test
    public void testGetAllAdmins() {
        List<Admin> expectedAdmins = Collections.singletonList(new Admin()); // Initialize with required data
        when(adminService.findAll()).thenReturn(expectedAdmins);

        List<Admin> retrievedAdmins = adminController.getAllAdmins();

        assertEquals(expectedAdmins, retrievedAdmins);
        verify(adminService, times(1)).findAll();
    }

    @Test
    public void testDeleteAdmin() {
        int adminId = 1;
        Admin adminToDelete = new Admin(); // Initialize with required data
        when(adminService.findById(adminId)).thenReturn(adminToDelete);
        when(adminService.delete(adminToDelete)).thenReturn(true);

        boolean result = adminController.deleteAdmin(adminId);

        assertEquals(true, result);
        verify(adminService, times(1)).findById(adminId);
        verify(adminService, times(1)).delete(adminToDelete);
    }

    @Test
    public void testLogin() {
        User user = new User(); // Initialize with required data
        String username = user.getUserName();
        String password = user.getPassword();
        Admin expectedAdmin = new Admin(); // Initialize with required data
        when(adminService.login(username, password)).thenReturn(expectedAdmin);

        ResponseEntity<String> response = adminController.login(user);

        assertEquals(new ResponseEntity<>("Login successful", HttpStatus.OK), response);
        verify(adminService, times(1)).login(username, password);
    }

    @Test
    public void testGetAdminByUsername() {
        String username = "testUsername";
        Admin expectedAdmin = new Admin(); // Initialize with required data
        when(adminRepository.findByUserName(username)).thenReturn(expectedAdmin);

        ResponseEntity<Admin> response = adminController.getAdminByUsername(username);

        assertEquals(new ResponseEntity<>(expectedAdmin, HttpStatus.OK), response);
        verify(adminRepository, times(1)).findByUserName(username);
    }
}
