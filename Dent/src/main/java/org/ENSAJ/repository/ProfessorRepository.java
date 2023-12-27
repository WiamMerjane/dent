package org.ENSAJ.repository;

import org.ENSAJ.entites.Admin;
import org.ENSAJ.entites.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
    Professor findByUserName(String userName);


}
