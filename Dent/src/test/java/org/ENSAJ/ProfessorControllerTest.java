package org.ENSAJ;

import org.ENSAJ.controller.ProfessorController;
import org.ENSAJ.entites.Professor;
import org.ENSAJ.entites.User;
import org.ENSAJ.repository.ProfessorRepository;
import org.ENSAJ.services.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfessorControllerTest {

    @Mock
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorController professorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProfessor() {
        Professor professorToCreate = new Professor(); // Initialize with required data
        when(professorService.create(professorToCreate)).thenReturn(professorToCreate);

        Professor createdProfessor = professorController.createProfessor(professorToCreate);

        assertEquals(professorToCreate, createdProfessor);
        verify(professorService, times(1)).create(professorToCreate);
    }

    @Test
    public void testUpdateProfessor() {
        int professorId = 1;
        Professor professorToUpdate = new Professor(); // Initialize with required data
        when(professorService.update(professorToUpdate)).thenReturn(professorToUpdate);

        Professor updatedProfessor = professorController.updateProfessor(professorId, professorToUpdate);

        assertEquals(professorToUpdate, updatedProfessor);
        verify(professorService, times(1)).update(professorToUpdate);
    }

    @Test
    public void testGetProfessorById() {
        int professorId = 1;
        Professor expectedProfessor = new Professor(); // Initialize with required data
        when(professorService.findById(professorId)).thenReturn(expectedProfessor);

        Professor retrievedProfessor = professorController.getProfessorById(professorId);

        assertEquals(expectedProfessor, retrievedProfessor);
        verify(professorService, times(1)).findById(professorId);
    }

    @Test
    public void testGetAllProfessors() {
        List<Professor> expectedProfessors = Arrays.asList(new Professor(), new Professor()); // Initialize with required data
        when(professorService.findAll()).thenReturn(expectedProfessors);

        List<Professor> retrievedProfessors = professorController.getAllProfessors();

        assertEquals(expectedProfessors, retrievedProfessors);
        verify(professorService, times(1)).findAll();
    }

    @Test
    public void testDeleteProfessor() {
        int professorId = 1;
        Professor professorToDelete = new Professor(); // Initialize with required data
        when(professorService.findById(professorId)).thenReturn(professorToDelete);
        when(professorService.delete(professorToDelete)).thenReturn(true);

        boolean result = professorController.deleteProfessor(professorId);

        assertEquals(true, result);
        verify(professorService, times(1)).findById(professorId);
        verify(professorService, times(1)).delete(professorToDelete);
    }

    @Test
    public void testLogin() {
        User user = new User(); // Initialize with required data
        String username = user.getUserName();
        String password = user.getPassword();
        Professor expectedProfessor = new Professor(); // Initialize with required data
        when(professorService.login(username, password)).thenReturn(expectedProfessor);

        ResponseEntity<String> response = professorController.login(user);

        assertEquals(new ResponseEntity<>("Login successful", HttpStatus.OK), response);
        verify(professorService, times(1)).login(username, password);
    }

    @Test
    public void testGetProfessorByUsername() {
        String username = "testUsername";
        Professor expectedProfessor = new Professor(); // Initialize with required data
        when(professorRepository.findByUserName(username)).thenReturn(expectedProfessor);

        ResponseEntity<Professor> response = professorController.getProfessorByUsername(username);

        assertEquals(new ResponseEntity<>(expectedProfessor, HttpStatus.OK), response);
        verify(professorRepository, times(1)).findByUserName(username);
    }
}

