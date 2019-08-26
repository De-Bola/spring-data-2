package com.sda.springdata2.chapter2.controller;

import com.sda.springdata2.chapter2.domain.Product;
import com.sda.springdata2.chapter2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product")
    public String showStartPage(Product product) {
        return "add-product";
    }

    @PostMapping("/product/add")
    public String addProduct(@Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-product";
        }
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "product-index";
    }

    @GetMapping("/product/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));
        productRepository.delete(product);
        model.addAttribute("products", productRepository.findAll());
        return "product-index";
    }

    @GetMapping("/product/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid product id: " + id));
        model.addAttribute("product", product);
        return "update-product";
    }
    @PostMapping("/product/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid Product product, BindingResult result, Model model){
        if (result.hasErrors()){
            product.setId(id);
            return "update-product";
        }
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "product-index";
    }
}
