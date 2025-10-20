package com.store.ecommerce.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.store.ecommerce.model.Product;
import com.store.ecommerce.repository.ProductRepo;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo prorep){
        this.productRepo = prorep;
    }

    public Product returnProductById(Long prodid){
        return productRepo.findById(prodid).orElseThrow(()-> new RuntimeException("Product not found!"));
    }

    public Product addProduct(Product prod){
        return productRepo.save(prod);
    }

    public ResponseEntity<Void> deleteProductByid(Long prodid){
        productRepo.deleteById(prodid);
        return ResponseEntity.noContent().build();
    }

    public List<Product> allProducts(){
        return productRepo.findAll();
    }

    public Product EditProduct(Long id, Product newProduct){
        Product oldProduct = productRepo.findById(id).orElseThrow(()-> new RuntimeException("Product not found!"));
        if (newProduct.getName() != null) {
            oldProduct.setName(newProduct.getName());
        }
        if (newProduct.getCategory() != null) {
            oldProduct.setCategory(newProduct.getCategory()); 
        }
        if (newProduct.getDescription() != null) {
            oldProduct.setDescription(newProduct.getDescription()); 
        }
        if (newProduct.getQuantity() != null) {
            oldProduct.setQuantity(newProduct.getQuantity());
        }

        return productRepo.save(oldProduct);
    }

    public void reduceStock(Product product, int quantityToReduce) {
        Long currentQuantity = product.getQuantity();

        if (currentQuantity == null || currentQuantity < quantityToReduce) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "Insufficient stock for product ID: " + product.getId() + 
                ". Requested: " + quantityToReduce + ", Available: " + currentQuantity);
        }
        
        product.setQuantity(currentQuantity - quantityToReduce);
        productRepo.save(product);
    }
}
