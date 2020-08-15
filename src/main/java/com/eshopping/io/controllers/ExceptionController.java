package com.eshopping.io.controllers;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class ExceptionController {

	@GetMapping("/403")
	public ResponseEntity<?> accessDenied(Principal user) {
		System.out.println("In Access denied controller");
		return ResponseEntity.ok().build();
	}
}
