package com.eshopping.io.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.io.model.Role;
import com.eshopping.io.model.User;
import com.eshopping.io.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*")
public class UserController {


	@Autowired 
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		User u = new User();
		u.setFirstname(user.getFirstname() );
		u.setUsername(user.getUsername() );
		u.setLastname(user.getLastname() );
//		u.setPassword(encoder.encode(user.getPassword()) );
		u.setPassword(user.getPassword());
		u.setEmail(user.getEmail() );
		
		Role role = new Role();
		role.setName("USER");
		u.setRoles(Arrays.asList(role));
		
		User savedUser = userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/login")
	public String login(@RequestHeader HttpHeaders creds, @RequestBody User user) {
		System.out.println(creds);
		System.out.println(user);
		//System.out.println(encoder.encode(user.getPassword()));
		
//		String substring = creds.substring(7, creds.length());
//		substring.split(":", substring.length())[0];
//		System.out.println("In login" + substring.split(":", substring.length())[0]);
//		User u = userRepo.findByUsername(substring.split(":", substring.length())[0]);
//		if(u == null) {
//			return "Error occured";
//		}
		return "authenticated successfully";
	}
}
