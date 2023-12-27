package org.ENSAJ.services;

import java.util.List;

import org.ENSAJ.dao.IDao;
import org.ENSAJ.entites.PW;
import org.ENSAJ.repository.PWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PWService implements IDao<PW> {

    @Autowired
    private PWRepository pwRepository;

    @Override
    public PW create(PW pw) {
        return pwRepository.save(pw);
    }

    @Override
    public PW update(PW pw) {
        // Check if the PW exists before updating
        if (pwRepository.existsById(pw.getId())) {
            return pwRepository.save(pw);
        }
        return null; // Handle the case where the PW doesn't exist
    }

    @Override
    public PW findById(int id) {
        return pwRepository.findById(id).orElse(null);
    }

    @Override
    public List<PW> findAll() {
        return pwRepository.findAll();
    }

    @Override
    public boolean delete(PW pw) {
        // Check if the PW exists before deleting
        if (pwRepository.existsById(pw.getId())) {
            pwRepository.delete(pw);
            return true;
        }
        return false; // Handle the case where the PW doesn't exist
    }
}
