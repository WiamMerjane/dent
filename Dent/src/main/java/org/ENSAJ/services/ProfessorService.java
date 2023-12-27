package org.ENSAJ.services;

import java.util.List;

import org.ENSAJ.dao.IDao;
import org.ENSAJ.entites.Admin;
import org.ENSAJ.entites.Professor;
import org.ENSAJ.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService implements IDao<Professor> {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public Professor create(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor update(Professor professor) {
        // Check if the professor exists before updating
        if (professorRepository.existsById(professor.getId())) {
            return professorRepository.save(professor);
        }
        return null; // Handle the case where the professor doesn't exist
    }

    @Override
    public Professor findById(int id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public boolean delete(Professor professor) {
        // Check if the professor exists before deleting
        if (professorRepository.existsById(professor.getId())) {
            professorRepository.delete(professor);
            return true;
        }
        return false; // Handle the case where the professor doesn't exist
    }

    public Professor login(String userName, String password) {
        Professor professor = professorRepository.findByUserName(userName);
        if (professor != null && professor.getPassword().equals(password)) {
            return professor;
        }
        return null; // Return null if login fails
    }
}
