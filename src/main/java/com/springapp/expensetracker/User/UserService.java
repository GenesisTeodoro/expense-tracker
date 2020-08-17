package com.springapp.expensetracker.User;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
	
	private UserRepository userRepository;
	
	private final ConfirmationTokenService confirmationTokenService;
	
	private final EmailSenderService emailSenderService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		final Optional<User> optionalUser = userRepository.findByEmail(email);
		
		return optionalUser.orElseThrow(() -> new UsernameNotFoundException(
				MessageFormat.format("User with email {0} doesn't exist.", email))  );
		
	}
	
	void signUpUser(User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		final ConfirmationToken confirmationToken = new ConfirmationToken(user);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
		
	}
	
	
	void confirmUser(ConfirmationToken confirmationToken) {
		final User user = confirmationToken.getUser();
		user.setEnabled(true);
		userRepository.save(user);
		confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
	}
	
	void sendConfirmationMail(String userMail, String token) {
		
		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userMail);
		mailMessage.setSubject("Expense Tracker - Mail Confirmation Link!");
		mailMessage.setFrom("galteracc@gmail.com");
		mailMessage.setText(
				"Thank you for registering. Please click on the below link"
				+ "to activate your account." + "http://localhost:8080/sign-up/confirm?token="
				+ token
				);
		
		emailSenderService.sendEmail(mailMessage);
		
	}
	
}
