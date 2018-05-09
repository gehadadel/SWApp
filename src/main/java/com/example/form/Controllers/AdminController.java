package com.example.form.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.form.Entities.Product;
import com.example.form.Entities.Statistics;
import com.example.form.Entities.Store_Product;
import com.example.form.Entities.User;
import com.example.form.Repository.ProductRepsitory;
import com.example.form.Repository.StatisticsRepository;
import com.example.form.Repository.Store_productRepository;
import com.example.form.Repository.UserRepository;
import com.example.form.Entities.Brand;
import com.example.form.Repository.BrandRepository;

@Controller
public class AdminController {
	
	@Autowired
	ProductRepsitory ProductRepo;
	@Autowired
	StatisticsRepository statrepo;
	@Autowired
	BrandRepository BrandRepo;
	@Autowired
	Store_productRepository store_productRepo;
	
	@Autowired
	UserRepository UserRepo;
	
	
    @GetMapping("/AdminHomePage")
    public String AdminHomePage() 
    {
        return "AdminHomePage";
    }
    
    
    @GetMapping("/AddProductAdmin")
    public String AddProductAdmin(Model model)
    {
        model.addAttribute("AddProductAdmin", new Product());
        return "AddProductAdmin";
    }
    
    @GetMapping("/AddBrandAdmin")
    public String AddBrandAdmin(Model mode2)
    {
        mode2.addAttribute("AddBrandAdmin", new Brand());
        return "AddBrandAdmin";
    }
    
    @PostMapping("/AddProductAdmin")
    public String AddProductAdmin(@ModelAttribute Product product) 
    {	
    	if(ProductRepo.existsById(product.getName()))
    	{
    		System.out.println("name exists");
    		return "AddProductAdmin";
    	}
    	else
    	{
    		ProductRepo.save(product);
    	}

    	return "AdminHomePage";
    }
    
    @PostMapping("/AddBrandAdmin")
    public String AddBrandAdmin(@ModelAttribute Brand brand) 
    {	
    	if(BrandRepo.existsById(brand.getName()))
    	{
    		System.out.println("name exists");
    		return "AddBrandAdmin";
    	}
    	else
    	{
    		BrandRepo.save(brand);
    	}

    	return "AddBrandAdmin";
    }
@GetMapping("/Statistics")
public String viewStat(Model modlle)
{
	Iterable<com.example.form.Entities.Statistics> lest = statrepo.findAll();
	ArrayList<Statistics> myarray= new ArrayList<>();
	for (Statistics s:myarray)
	{
		myarray.add(s);
	}
	modlle.addAttribute("",myarray);//To_Do
	
	return "Statistics";
}

@GetMapping("/Statistics")
public String approvstat(HttpServletRequest req)
{
	statrepo.deleteById(req.getParameter("")); 
	statrepo.save(new com.example.form.Entities.Statistics(req.getParameter(""),true));
	return "/Statistics";

}

public String viewProductStatistics (Model model)
{
	Iterable<Product> product = ProductRepo.findAll();
	HashMap<String,Integer > pro =new HashMap<>();
	for(Product p :product )
	{
	pro.put(p.getName(),0);
	}
	Iterable<Store_Product> storeproduct = store_productRepo.findAll();
	for(Store_Product p :storeproduct )
	{
	pro.put(p.getID().split("@")[1],pro.get(p.getID().split("@")[1]).intValue()+p.getBuyed());
	}
	Pair<String ,Integer> highProdut= getHighestProduct(pro);
	Pair<String ,Integer> lowestProdut= getLowestProduct(pro);
	int AvrageProduct=getAvrageProduct(pro);
	int sumProduct=getSumProduct(pro);
	
	
	model.addAttribute("hproduct",highProdut.getFirst());
	model.addAttribute("Hnum",highProdut.getSecond());
	model.addAttribute("LProduct",lowestProdut.getFirst());
	model.addAttribute("Lnum",lowestProdut.getSecond());
	
	
	
	
	Iterable<User> user = UserRepo.findAll();
	HashMap<String,Integer > use =new HashMap<>();
	for(User u :user )
	{
	use.put(u.getName(),0);
	}
	
	/*Iterable<Store_Product> storeproduct = store_productRepo.findAll();
	for(Store_Product p :storeproduct )
	{
	pro.put(p.getID().split("@")[1],pro.get(p.getID().split("@")[1]).intValue()+p.getBuyed());
	}
	
	*/
	Pair<String ,Integer> highuser= getHighestuser(use);
	Pair<String ,Integer> lowestuser= getLowestuser(use);
	int Avrageuser=getAvrageuser(use);
	int sumuser=getSumuser(use);
	
	
	model.addAttribute("hproduct",highProdut.getFirst());
	model.addAttribute("Hnum",highProdut.getSecond());
	model.addAttribute("LProduct",lowestProdut.getFirst());
	model.addAttribute("Lnum",lowestProdut.getSecond());
	
	
	return"Statistics"; 
}


private Pair<String, Integer> getLowestProduct(HashMap<String, Integer> pro) {
	Pair<String,Integer> lowproduct = null;
	int LowestProduct ; 
	String LowestProductName;
    int maxValueInMap=(Collections.min(pro.values()));
    for (Entry<String, Integer> entry : pro.entrySet()) {  // Itrate through hashmap
    if (entry.getValue()==maxValueInMap) {
        System.out.println(entry.getKey());     // Print the key with max value
        LowestProductName=entry.getKey();
        LowestProduct=entry.getValue();
        lowproduct.getFirst();
        lowproduct.getSecond();
    }
    }
	return lowproduct;
}


private Pair<String, Integer> getHighestProduct(HashMap<String, Integer> pro) {
	Pair<String,Integer> highproduct = null;
	int HighestProduct ; 
	String HighestProductName;
    int maxValueInMap=(Collections.max(pro.values()));
    for (Entry<String, Integer> entry : pro.entrySet()) {  // Itrate through hashmap
    if (entry.getValue()==maxValueInMap) {
        System.out.println(entry.getKey());     // Print the key with max value
        HighestProductName=entry.getKey();
        HighestProduct=entry.getValue();
       highproduct.getFirst();
       highproduct.getSecond();
    }
    }

	return highproduct;
    
}


private int getSumProduct(HashMap<String, Integer> pro) {
	Iterable<Product> product = ProductRepo.findAll();
	int counter=0;
	for(Product p :product )
	{
	counter++;
	}

	return counter;
}


private int getAvrageProduct(HashMap<String, Integer> pro) {

	Iterable<Product> product = ProductRepo.findAll();
	int counter=0;
	int average ;  
	int sum=0;
	for(Product p :product )
	{
    sum+=p.getQuntity();
	counter++;
	}
	average=counter/sum;
	
	return average;
}


private Pair<String, Integer> getHighestuser(HashMap<String, Integer> ue)
{
	Pair<String,Integer> highuser = null;
	int HighestUser ; 
	String HighestuserName;
    int maxValueInMap=(Collections.max(ue.values()));
    for (Entry<String, Integer> entry : ue.entrySet()) {  // Itrate through hashmap
    if (entry.getValue()==maxValueInMap) {
        System.out.println(entry.getKey());     // Print the key with max value
        HighestuserName=entry.getKey();
        HighestUser=entry.getValue();
        highuser.getFirst();
        highuser.getSecond();
    }
    }

	return highuser;
	
}

private Pair<String, Integer> getLowestuser(HashMap<String, Integer> ue)
{
	Pair<String,Integer> lowuser = null;
	int HighestUser ; 
	String HighestuserName;
    int maxValueInMap=(Collections.min(ue.values()));
    for (Entry<String, Integer> entry : ue.entrySet()) {  // Itrate through hashmap
    if (entry.getValue()==maxValueInMap) {
        System.out.println(entry.getKey());     // Print the key with max value
        HighestuserName=entry.getKey();
        HighestUser=entry.getValue();
        lowuser.getFirst();
        lowuser.getSecond();
    }
    }

	return lowuser;
	
}

private int getSumuser(HashMap<String, Integer> use) {

	Iterable<User> user = UserRepo.findAll();
	int counter=0;
	for(User u :user )
	{
	counter++;
	}

	return counter;
}


private int getAvrageuser(HashMap<String, Integer> use) {

	int avg = 0;
	Iterable<User> user = UserRepo.findAll();
	int counter=0;
	int sum=0;
	for(User u :user )
	{
	sum+=u.getNumofusers();
	counter++;
	}
	avg=counter/sum;

	return avg;
}



@PostMapping("/Statistics")
public String aprovestat(HttpServletRequest req)
{
	statrepo.deleteById(req.getParameter(""));
	statrepo.save(new com.example.form.Entities.Statistics (req.getParameter(""),true));
	
	return "Statistics";
}



    

}
