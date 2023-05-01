package com.ramesh.springcloud.controller;

import com.ramesh.springcloud.dto.Coupon;
import com.ramesh.springcloud.model.Product;
import com.ramesh.springcloud.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    @Autowired
    private ProductRepo repo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${couponServiceURL}")
    private String couponServiceURL;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        Coupon coupon = restTemplate.getForObject(couponServiceURL + product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }

    @GetMapping(value = "/products/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return repo.findById(id);
    }

}
