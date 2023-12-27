package org.ENSAJ.controller;

import org.ENSAJ.entites.Groupe;
import org.ENSAJ.entites.PW;
import org.ENSAJ.services.GroupService;
import org.ENSAJ.services.PWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/groupes")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {

    private final GroupService groupeService;
    private final PWService pwService;

    @Autowired
    public GroupController(GroupService groupeService, PWService pwService) {
        this.groupeService = groupeService;
        this.pwService = pwService;
    }
    @PostMapping
    public Groupe createGroupe(@RequestBody Groupe groupe) {
        return groupeService.create(groupe);
    }

    @PutMapping("/{id}")
    public Groupe updateGroupe(@PathVariable int id, @RequestBody Groupe groupe) {
        groupe.setId(id);
        return groupeService.update(groupe);
    }

    @GetMapping("/{id}")
    public Groupe getGroupeById(@PathVariable int id) {
        return groupeService.findById(id);
    }

    @GetMapping
    public List<Groupe> getAllGroupes() {
        return groupeService.findAll();
    }

    @DeleteMapping("/{id}")
    public boolean deleteGroupe(@PathVariable int id) {
        Groupe groupe = groupeService.findById(id);
        if (groupe != null) {
            return groupeService.delete(groupe);
        }
        return false;
    }

    @PostMapping("/{groupId}/add-pw/{pwId}")
    public Groupe addPWToGroup(@PathVariable int groupId, @PathVariable int pwId) {
        Groupe groupe = groupeService.findById(groupId);
        PW pw = pwService.findById(pwId);

        if (groupe != null && pw != null) {
            groupe.getPws().add(pw);
            pw.getGroups().add(groupe); // Assurez-vous d'ajouter le groupe au PW également
            groupeService.update(groupe);
        }

        return null; // Vous pouvez ajuster le comportement en conséquence
    }

    // Ajouter une méthode pour supprimer un PW d'un groupe
    @DeleteMapping("/{groupId}/remove-pw/{pwId}")
    public Groupe removePWFromGroup(@PathVariable int groupId, @PathVariable int pwId) {
        Groupe groupe = groupeService.findById(groupId);
        PW pw = pwService.findById(pwId);

        if (groupe != null && pw != null) {
            groupe.getPws().remove(pw);
            return groupeService.update(groupe);
        }

        return null; // Vous pouvez ajuster le comportement en conséquence
    }
    @GetMapping("/{groupId}/pws")
    public List<PW> getPWsForGroup(@PathVariable int groupId) {
        Groupe groupe = groupeService.findById(groupId);

        if (groupe != null) {
            return groupe.getPws();
        }

        return Collections.emptyList(); // Retourne une liste vide si le groupe n'est pas trouvé
    }


}
