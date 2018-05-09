package com.example.form.Controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.Entities.Action;
import com.example.form.Entities.Brand;
import com.example.form.Entities.Product;
import com.example.form.Entities.Store;
import com.example.form.Entities.Store_Product;
import com.example.form.Repository.ActionRepository;
import com.example.form.Repository.BrandRepository;
import com.example.form.Repository.ProductRepsitory;
import com.example.form.Repository.StoreRepository;
import com.example.form.Repository.Store_productRepository;

@Controller
public class StoreOwnerController {
	@Autowired
	StoreRepository StoreRepo ;
	@Autowired 
	ProductRepsitory productRepo;
	@Autowired
	Store_productRepository StoreproductRepo;
	@Autowired 
	ActionRepository ActionRepo;
	
	@Autowired 
	BrandRepository BrandRepo;
	@GetMapping("/StoreOwnerHomePage")
	
	public String StoreOwnerHomePage()
	{
		return "StoreOwnerHomePage";
		
	}
	
	@PostMapping("/AddStore")
	public String AddStore(HttpServletRequest request)
	{
		String Name=request.getParameter("Name");
		String Location=request.getParameter("Location");
		String Type=request.getParameter("type");
		Store store=new Store(Name,Location,Type);
		if(!StoreRepo.existsById(Name))
		{
			StoreRepo.save(store);
			
		}
		return "AddStore";  //return html page

	}
	
	@GetMapping("/AddStore")
	
	public String AddStore()
	{
		return "AddStore" ;
	}
	@GetMapping("/AddProductToStore")
	public String Showaddproduct (Model model)
	{
		Iterable<Product> lest = productRepo.findAll();
		ArrayList<Product>productlist = new ArrayList<>();
		for(Product p :lest)
		{
			productlist.add(p);
		}
		model.addAttribute("product",productlist);

		Iterable<Brand> Blest = BrandRepo.findAll();
		ArrayList<Brand>Brandlist = new ArrayList<>();
		for(Brand B :Blest)
		{
			Brandlist.add(B);
		}
		model.addAttribute("Brand",Brandlist);
		
		return "AddProductStoreOwner";
	}
	@PostMapping("/AddProductToStore")
	public String SaveProduct(HttpServletRequest request)
	{
		String StoreID =request.getParameter("");
		String ProductId = request.getParameter("product");
		String brand = request.getParameter("brand");
		Product product = productRepo.findById(ProductId).get();
		Store store =StoreRepo.findById(StoreID).get(); 
		double price = Double.parseDouble(request.getParameter("price"));
		int Quan = Integer.parseInt(request.getParameter("quan"));
		String ID = StoreID+"@"+ProductId;
		Store_Product product2 =new Store_Product(ID, product, store, brand, Quan, price, 0, 0); 
		StoreproductRepo.save(product2);
		return "AddProductStoreOwner" ;
	}
	
	@PostMapping("/Action")
	public String DeleteProduct(HttpServletRequest request)
	{
		Action action=new Action();
		String ID =action.getDeleteID();
		ID = request.getParameter("product");
		Product product = productRepo.findById(ID).get();
		Store_Product product1 =new Store_Product(ID, product);
		ActionRepo.deleteById(ID);
		return "Action";
	}
	
	@PostMapping("/Action")
	public String EditProduct(HttpServletRequest request)
	{
		AdminController admin=new AdminController();
		Action action=new Action();
		String ID=action.getEditID();
		ID = request.getParameter("product");
		Product product = productRepo.findById(ID).get();
		Store_Product product1 =new Store_Product(ID, product);
		ActionRepo.deleteById(ID);
		admin.AddProductAdmin(product);
		return "Action";
	}
	
	 
	
	 
	
	
}
