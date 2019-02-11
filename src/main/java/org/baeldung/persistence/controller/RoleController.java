package org.baeldung.persistence.controller;

import org.baeldung.persistence.katharsis.RoleResourceRepository;
import org.baeldung.persistence.model.Role;
import org.baeldung.persistence.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
	
	@Autowired RoleService service;
	
	@PostMapping("save")
	public boolean save(@RequestBody Role role) {
	return service.save(role);
	}
	

}