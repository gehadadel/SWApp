package com.example.form.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.form.Entities.User;
import com.example.form.Repository.UserRepository;

@Controller
public class AccountController {

	@Autowired
	UserRepository userRepo;
	

	@GetMapping("/index")
    public String SigninForm() {return "index";}
	
    @PostMapping("/index")
    public String Signin(@RequestParam String name,@RequestParam String password) 
    {
        try
        {        	
        	User acc=userRepo.findById(name).get();
        	System.out.println(acc.getName()+"   "+acc.getPassword()+"  "+acc.getType());
        	if(acc!=null&&acc.getPassword().equals(password)) 
        	{
        		switch(acc.getType()) 
        		{
        			case "admin":
        				return"AdminHomePage";
        			case "StoreOwner":
        				return "StowerOwnerHomePage";
        			case "Customer":
        				return "CustomerHomePage";
        			default:
        				return"notYet";
        		
        		}
        	}
        }
        catch(NullPointerException e)
        {
        	System.out.println("not found");
        	return "index";
        } 
        catch (Exception e) {
		
		}
		return "index";
       
    }
    
    
    @GetMapping("/signup")
    public String SignupForm() {return "signup";}
	
    @PostMapping("/signup")
    public String Signup(@RequestParam String username,@RequestParam String pass1,@RequestParam String pass2,@RequestParam String type) 
    {
        if(userRepo.existsById(username)) 
        {
        	System.out.println("UserName Already Exists"+"\n");
    		return "signup";
        }
        if(!(pass1.equals(pass2))) 
        {
        	System.out.println("Password is not the same"+"\n");
    		return "signup";
        }
    	userRepo.save(new User(username,pass1,type));
        return "index";
    }
    
    
    
    
}
