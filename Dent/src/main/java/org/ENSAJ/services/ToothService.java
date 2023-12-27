package org.ENSAJ.services;

import java.util.List;
import java.util.Optional;

import org.ENSAJ.dao.IDao;
import org.ENSAJ.entites.Tooth;
import org.ENSAJ.repository.ToothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToothService implements IDao<Tooth> {

	@Autowired
    private  ToothRepository toothRepository;

    
    public ToothService(ToothRepository toothRepository) {
        this.toothRepository = toothRepository;
    }

    @Override
    public Tooth create(Tooth tooth) {
        return toothRepository.save(tooth);
    }

    @Override
    public Tooth update(Tooth tooth) {
        return toothRepository.save(tooth);
    }

    @Override
    public Tooth findById(int id) {
        Optional<Tooth> optionalTooth = toothRepository.findById(id);
        return optionalTooth.orElse(null);
    }

    @Override
    public List<Tooth> findAll() {
        return toothRepository.findAll();
    }

    @Override
    public boolean delete(Tooth tooth) {
        try {
            toothRepository.delete(tooth);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
