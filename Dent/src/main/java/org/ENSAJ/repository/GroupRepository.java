package org.ENSAJ.repository;

import org.ENSAJ.entites.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 

@Repository
public interface GroupRepository extends JpaRepository<Groupe, Integer>{

}
