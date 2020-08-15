package com.eshopping.io.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
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
import com.eshopping.io.model.Roles;
import com.eshopping.io.model.User;
import com.eshopping.io.repository.UserRepository;

@RestController
@RequestMapping("/eshopping/users")
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
		u.setId(user.getId());
		u.setFirstname(user.getFirstname() );
		u.setUsername(user.getUsername() );
		u.setLastname(user.getLastname() );
		u.setPassword(encoder.encode(user.getPassword()) );
		u.setEmail(user.getEmail() );
		
		Role role = new Role();
		role.setName(Roles.ADMIN.name());
		role.setId(Roles.ADMIN.ordinal());
		u.setRoles(Arrays.asList(role));
		
		User savedUser = userRepo.save(u);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestHeader HttpHeaders headers) {
		System.out.println(headers);
//		System.out.println(user);
		String authHeader = headers.get("authorization").get(0);
		String encodedCredentials = authHeader.substring(6, authHeader.length());
		String username = new String(Base64.decodeBase64(encodedCredentials)).split(":")[0];
		User u = userRepo.findByUsername(username);
		if(u == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(u);
	}
}
