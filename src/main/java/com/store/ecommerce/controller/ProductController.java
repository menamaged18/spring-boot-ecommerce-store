package com.store.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.ecommerce.model.Product;
import com.store.ecommerce.service.ProductService;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/product/")
public class ProductController {
    private final ProductService prodServ;

    public ProductController(ProductService _ProductService){
        this.prodServ = _ProductService;
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product prod) {
        Product newProduct = prodServ.addProduct(prod);
        // Return a 201 Created status
        return ResponseEntity.created(
            // (Optional but Recommended) Include the location of the new resource
            URI.create("/products/" + newProduct.getId())
        ).body(newProduct); 
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id) {
        return prodServ.returnProductById(id);
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return prodServ.allProducts();
    }
    
    @PutMapping("edit/{id}")
    public Product editProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        return prodServ.EditProduct(id, newProduct);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        prodServ.deleteProductByid(id);
        return ResponseEntity.noContent().build();
    }
}
