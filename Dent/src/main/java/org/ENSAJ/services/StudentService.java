package org.ENSAJ.services;

import java.util.List;

import org.ENSAJ.dao.IDao;
import org.ENSAJ.entites.Admin;
import org.ENSAJ.entites.Student;
import org.ENSAJ.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IDao<Student> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        // Check if the student exists before updating
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        return null; // Handle the case where the student doesn't exist
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public boolean delete(Student student) {
        // Check if the student exists before deleting
        if (studentRepository.existsById(student.getId())) {
            studentRepository.delete(student);
            return true;
        }
        return false; // Handle the case where the student doesn't exist
    }

    public Student login(String userName, String password) {
        Student student = studentRepository.findByUserName(userName);
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null; // Return null if login fails
    }
}