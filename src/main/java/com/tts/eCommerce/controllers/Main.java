package com.tts.eCommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.tts.eCommerce.model.Product;
import com.tts.eCommerce.services.ProductService;

@Controller
public class Main {
	@Autowired
    ProductService productService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @ModelAttribute("products")
    public List<Product> products() {
        return productService.findAll();
    }

    @ModelAttribute("categories")
    public List<String> categories() {
        return productService.findDistinctCategories();
    }

    @ModelAttribute("brands")
    public List<String> brands() {
        return productService.findDistinctBrands();
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String category,
                         @RequestParam(required = false) String brand,
                         Model model) {
        List<Product> filtered = productService.findByBrandAndOrCategory(brand, category);
        model.addAttribute("products", filtered); // Overrides the @ModelAttribute above.
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
   
}
