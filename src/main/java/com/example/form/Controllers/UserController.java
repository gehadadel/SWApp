package com.example.form.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.Entities.Product;
import com.example.form.Repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository UserRepo;
	
	@GetMapping("/UserHomePage")
    public String AdminHomePage() 
    {
        return "UserHomePage";
    }
	
	@GetMapping("/BuyProduct")
    public String BuyProduct(Model model)
    {
        model.addAttribute("BuyProduct", new Product());
        return "BuyProduct";
    }
	
	@PostMapping("/BuyProduct")
    public String BuyProduct(@ModelAttribute Product product) 
    {	
    	if(UserRepo.existsById(product.getName()))
    	{
    		System.out.println("name exists");
    		return "BuyProduct";
    	}
    	else
    	{
    		///////////////
    		
    		
    		
    	}

    	return "UserHomePage";
    }

}
