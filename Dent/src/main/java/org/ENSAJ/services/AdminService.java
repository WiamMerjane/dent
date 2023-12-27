package org.ENSAJ.services;

import java.util.List;

import org.ENSAJ.dao.IDao;
import org.ENSAJ.entites.Admin;
import org.ENSAJ.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IDao<Admin> {

	 @Autowired
	    private AdminRepository adminRepository;

	    @Override
	    public Admin create(Admin admin) {
	        return adminRepository.save(admin);
	    }

	    @Override
	    public Admin update(Admin admin) {
	        // Check if the admin exists before updating
	        if (adminRepository.existsById(admin.getId())) {
	            return adminRepository.save(admin);
	        }
	        return null; // Handle the case where the admin doesn't exist
	    }

	    @Override
	    public Admin findById(int id) {
	        return adminRepository.findById(id).orElse(null);
	    }

	    @Override
	    public List<Admin> findAll() {
	        return adminRepository.findAll();
	    }

	    @Override
	    public boolean delete(Admin admin) {
	        // Check if the admin exists before deleting
	        if (adminRepository.existsById(admin.getId())) {
	            adminRepository.delete(admin);
	            return true;
	        }
	        return false; // Handle the case where the admin doesn't exist
	    }

//	public boolean authenticate(String userName, String password) {
//		// Implémentez ici la logique d'authentification
//		Admin admin = adminRepository.findByUserName(userName);
//		return admin != null && admin.getPassword().equals(password);
//	}

	public Admin login(String userName, String password) {
		Admin admin = adminRepository.findByUserName(userName);
		if (admin != null && admin.getPassword().equals(password)) {
			return admin;
		}
		return null; // Return null if login fails
	}



  
}
