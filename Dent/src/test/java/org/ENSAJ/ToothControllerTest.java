package org.ENSAJ;

import org.ENSAJ.controller.ToothController;
import org.ENSAJ.entites.Tooth;
import org.ENSAJ.services.ToothService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ToothControllerTest {

    @Mock
    private ToothService toothService;

    @InjectMocks
    private ToothController toothController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTooth() {
        Tooth toothToCreate = new Tooth(); // Initialize with required data
        when(toothService.create(toothToCreate)).thenReturn(toothToCreate);

        Tooth createdTooth = toothController.createTooth(toothToCreate);

        assertEquals(toothToCreate, createdTooth);
        verify(toothService, times(1)).create(toothToCreate);
    }

    @Test
    public void testUpdateTooth() {
        int toothId = 1;
        Tooth toothToUpdate = new Tooth(); // Initialize with required data
        when(toothService.update(toothToUpdate)).thenReturn(toothToUpdate);

        Tooth updatedTooth = toothController.updateTooth(toothId, toothToUpdate);

        assertEquals(toothToUpdate, updatedTooth);
        verify(toothService, times(1)).update(toothToUpdate);
    }

    @Test
    public void testGetToothById() {
        int toothId = 1;
        Tooth expectedTooth = new Tooth(); // Initialize with required data
        when(toothService.findById(toothId)).thenReturn(expectedTooth);

        Tooth retrievedTooth = toothController.getToothById(toothId);

        assertEquals(expectedTooth, retrievedTooth);
        verify(toothService, times(1)).findById(toothId);
    }

    @Test
    public void testGetAllTeeth() {
        List<Tooth> expectedTeeth = Arrays.asList(new Tooth(), new Tooth()); // Initialize with required data
        when(toothService.findAll()).thenReturn(expectedTeeth);

        List<Tooth> retrievedTeeth = toothController.getAllTeeth();

        assertEquals(expectedTeeth, retrievedTeeth);
        verify(toothService, times(1)).findAll();
    }

    @Test
    public void testDeleteTooth() {
        int toothId = 1;
        Tooth toothToDelete = new Tooth(); // Initialize with required data
        when(toothService.findById(toothId)).thenReturn(toothToDelete);
        when(toothService.delete(toothToDelete)).thenReturn(true);

        boolean result = toothController.deleteTooth(toothId);

        assertEquals(true, result);
        verify(toothService, times(1)).findById(toothId);
        verify(toothService, times(1)).delete(toothToDelete);
    }
}

