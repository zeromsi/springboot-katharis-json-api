package org.baeldung.persistence.dao;

import java.util.List;

import org.baeldung.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
@Query("Select u from User u where u.id=:userId")
	User findByUserId(@Param("userId") Long id);
@Query("Select u from User u where u.id IN (:userId)")
List<User> findAllIds(@Param("userId")Iterable<Long> ids);

}