package com.springapp.expensetracker.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long>{

	Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
	
}
