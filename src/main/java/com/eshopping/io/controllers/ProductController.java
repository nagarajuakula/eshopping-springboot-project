package com.eshopping.io.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.io.model.Product;
import com.eshopping.io.model.User;
import com.eshopping.io.repository.ProductRepository;
import com.eshopping.io.repository.UserRepository;

@RestController
@RequestMapping("/eshopping")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		List<Product> products = productRepo.findAll();
//		products.stream().forEach(System.out::print);
		return products;
	}
	
	
	
	@PostMapping("/products/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product savedProduct = productRepo.save(product);
		System.out.println("Product added successfully");
		
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
	}
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Product currentProduct = productRepo.findById(product.getId()).stream().findFirst().orElse(null);
		if(currentProduct != null) {
			System.out.println(product.getDescription());
			productRepo.save(product);
		} else {
			System.out.println("No user with id " + product.getId() + " not found");
		}
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productRepo.deleteById(id);
	}
}
