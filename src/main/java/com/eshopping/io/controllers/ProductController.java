package com.eshopping.io.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.io.model.Product;
import com.eshopping.io.repository.ProductRepository;

@RestController
@RequestMapping("/eshopping")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		List<Product> products = productRepo.findAll();

		return products;
	}
	
	
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product savedProduct = productRepo.save(product);
		
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
	}
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception {
		Product currentProduct = productRepo.findById(product.getId()).stream().findFirst().orElseThrow(() -> new Exception("Product with id " + product.getId() + " not found"));
		
		currentProduct.setName(product.getName());
		currentProduct.setDescription(product.getDescription());
		currentProduct.setCategory(product.getCategory());
		currentProduct.setAuthor(product.getAuthor());
		currentProduct.setPrice(product.getPrice());
		
		Product updatedProduct = productRepo.save(currentProduct);
		System.out.println("In update" + updatedProduct);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable("id") Long id) throws Exception {
		Product currentProduct = productRepo.findById(id).stream().findFirst().orElseThrow(() -> new Exception("Product with id " + id + " not found"));
		productRepo.delete(currentProduct);
		
		return "Deleted";
	}
}
