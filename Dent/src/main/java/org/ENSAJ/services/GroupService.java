package org.ENSAJ.services;

import java.util.List;

import org.ENSAJ.dao.IDao;
import org.ENSAJ.entites.Groupe;
import org.ENSAJ.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService implements IDao<Groupe> {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Groupe create(Groupe group) {
        return groupRepository.save(group);
    }

    @Override
    public Groupe update(Groupe group) {
        // Check if the groupe exists before updating
        if (groupRepository.existsById(group.getId())) {
            return groupRepository.save(group);
        }
        return null; // Handle the case where the groupe doesn't exist
    }

    @Override
    public Groupe findById(int id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public List<Groupe> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public boolean delete(Groupe group) {
        // Check if the groupe exists before deleting
        if (groupRepository.existsById(group.getId())) {
            groupRepository.delete(group);
            return true;
        }
        return false; // Handle the case where the groupe doesn't exist
    }
}
