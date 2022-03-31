package com.bootstrap.dao.repositories.jpa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bootstrap.dao.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);

	Optional<User> findByEmail(String email);

}
