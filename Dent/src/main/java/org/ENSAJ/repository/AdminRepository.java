package org.ENSAJ.repository;

import org.ENSAJ.entites.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUserName(String userName);


}
