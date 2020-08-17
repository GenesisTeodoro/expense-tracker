package com.springapp.expensetracker.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends CrudRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
