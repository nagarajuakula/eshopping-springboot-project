package com.eshopping.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshopping.io.model.Product;

//@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
