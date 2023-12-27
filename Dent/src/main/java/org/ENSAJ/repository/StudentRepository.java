package org.ENSAJ.repository;

import org.ENSAJ.entites.Admin;
import org.ENSAJ.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByUserName(String userName);

}