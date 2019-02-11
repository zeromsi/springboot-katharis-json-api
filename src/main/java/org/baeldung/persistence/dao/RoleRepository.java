package org.baeldung.persistence.dao;

import java.util.List;

import org.baeldung.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("Select r from Role r where r.id=:userId")
	Role findByRoleId(@Param("userId") Long id);

	@Query("Select r from Role r where r.id IN (:userId)")
	List<Role> findAllIds(@Param("userId") Iterable<Long> ids);
}