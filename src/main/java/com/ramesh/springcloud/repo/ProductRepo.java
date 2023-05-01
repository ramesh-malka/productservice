package com.ramesh.springcloud.repo;

import com.ramesh.springcloud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
