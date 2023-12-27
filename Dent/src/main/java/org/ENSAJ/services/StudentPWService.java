package org.ENSAJ.services;

import java.util.List;

import org.ENSAJ.dao.IDao;
import org.ENSAJ.entites.StudentPW;
import org.ENSAJ.repository.StudentPWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentPWService implements IDao<StudentPW> {

    @Autowired
    private StudentPWRepository studentPWRepository;

    @Override
    public StudentPW create(StudentPW studentPW) {
        return studentPWRepository.save(studentPW);
    }

    @Override
    public StudentPW update(StudentPW studentPW) {
//        // Check if the StudentPW exists before updating
//        if (studentPWRepository.existsById(studentPW.getId())) {
//            return studentPWRepository.save(studentPW);
//        }
        return null; // Handle the case where the StudentPW doesn't exist
    }

    @Override
    public StudentPW findById(int id) {
        return studentPWRepository.findById(id).orElse(null);
    }

    @Override
    public List<StudentPW> findAll() {
        return studentPWRepository.findAll();
    }

    @Override
    public boolean delete(StudentPW studentPW) {
//        // Check if the StudentPW exists before deleting
//        if (studentPWRepository.existsById(studentPW.getId())) {
//            studentPWRepository.delete(studentPW);
//            return true;
//        }
        return false; // Handle the case where the StudentPW doesn't exist
    }
}
