package org.baeldung.persistence.service;

import org.baeldung.persistence.katharsis.RoleResourceRepository;
import org.baeldung.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Autowired RoleResourceRepository repository;
	
	public boolean save(Role role) {
		try {
			repository.save(role);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	

}