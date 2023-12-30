package org.ENSAJ;

import org.ENSAJ.controller.GroupController;
import org.ENSAJ.entites.Groupe;
import org.ENSAJ.entites.PW;
import org.ENSAJ.services.GroupService;
import org.ENSAJ.services.PWService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GroupControllerTest {

    @Mock
    private GroupService groupService;

    @Mock
    private PWService pwService;

    @InjectMocks
    private GroupController groupController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateGroupe() {
        Groupe groupeToCreate = new Groupe(); // Initialize with required data
        when(groupService.create(groupeToCreate)).thenReturn(groupeToCreate);

        Groupe createdGroupe = groupController.createGroupe(groupeToCreate);

        assertEquals(groupeToCreate, createdGroupe);
        verify(groupService, times(1)).create(groupeToCreate);
    }

    @Test
    public void testUpdateGroupe() {
        int groupId = 1;
        Groupe groupeToUpdate = new Groupe(); // Initialize with required data
        when(groupService.update(groupeToUpdate)).thenReturn(groupeToUpdate);

        Groupe updatedGroupe = groupController.updateGroupe(groupId, groupeToUpdate);

        assertEquals(groupeToUpdate, updatedGroupe);
        verify(groupService, times(1)).update(groupeToUpdate);
    }

    @Test
    public void testGetGroupeById() {
        int groupId = 1;
        Groupe expectedGroupe = new Groupe(); // Initialize with required data
        when(groupService.findById(groupId)).thenReturn(expectedGroupe);

        Groupe retrievedGroupe = groupController.getGroupeById(groupId);

        assertEquals(expectedGroupe, retrievedGroupe);
        verify(groupService, times(1)).findById(groupId);
    }

    @Test
    public void testGetAllGroupes() {
        List<Groupe> expectedGroupes = Collections.singletonList(new Groupe()); // Initialize with required data
        when(groupService.findAll()).thenReturn(expectedGroupes);

        List<Groupe> retrievedGroupes = groupController.getAllGroupes();

        assertEquals(expectedGroupes, retrievedGroupes);
        verify(groupService, times(1)).findAll();
    }

    @Test
    public void testDeleteGroupe() {
        int groupId = 1;
        Groupe groupeToDelete = new Groupe(); // Initialize with required data
        when(groupService.findById(groupId)).thenReturn(groupeToDelete);
        when(groupService.delete(groupeToDelete)).thenReturn(true);

        boolean result = groupController.deleteGroupe(groupId);

        assertEquals(true, result);
        verify(groupService, times(1)).findById(groupId);
        verify(groupService, times(1)).delete(groupeToDelete);
    }

    @Test
    public void testAddPWToGroup() {
        int groupId = 1;
        int pwId = 2;
        Groupe groupe = new Groupe(); // Initialize with required data
        PW pw = new PW(); // Initialize with required data
        when(groupService.findById(groupId)).thenReturn(groupe);
        when(pwService.findById(pwId)).thenReturn(pw);

        Groupe result = groupController.addPWToGroup(groupId, pwId);

        assertEquals(groupe, result);
        verify(groupService, times(1)).findById(groupId);
        verify(pwService, times(1)).findById(pwId);
        verify(groupService, times(1)).update(groupe);
    }

    @Test
    public void testRemovePWFromGroup() {
        int groupId = 1;
        int pwId = 2;
        Groupe groupe = new Groupe(); // Initialize with required data
        PW pw = new PW(); // Initialize with required data
        when(groupService.findById(groupId)).thenReturn(groupe);
        when(pwService.findById(pwId)).thenReturn(pw);
        when(groupService.update(groupe)).thenReturn(groupe);

        Groupe result = groupController.removePWFromGroup(groupId, pwId);

        assertEquals(groupe, result);
        verify(groupService, times(1)).findById(groupId);
        verify(pwService, times(1)).findById(pwId);
        verify(groupService, times(1)).update(groupe);
    }

    @Test
    public void testGetPWsForGroup() {
        int groupId = 1;
        Groupe groupe = new Groupe(); // Initialize with required data
        when(groupService.findById(groupId)).thenReturn(groupe);

        List<PW> expectedPWs = Arrays.asList(new PW(), new PW()); // Initialize with required data
        groupe.setPws(expectedPWs);

        List<PW> retrievedPWs = groupController.getPWsForGroup(groupId);

        assertEquals(expectedPWs, retrievedPWs);
        verify(groupService, times(1)).findById(groupId);
    }
}
