package com.authenticationfirebasespringboot.controllers;

import com.authenticationfirebasespringboot.dtos.Product;
import com.authenticationfirebasespringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("/saveProduct")
    public String saveProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.saveProduct(product);
    }

    @PutMapping ("/updateProduct")
    public String updateProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.updateProduct(product);
    }

    @DeleteMapping ("/deleteProduct")
    public String deleteProduct(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.deleteProduct(product);
    }
    @GetMapping ("/getProduct")
    public Product getProducts(@RequestBody Product product) throws ExecutionException, InterruptedException {
        return productService.getProduct(product.getName());
    }
}
