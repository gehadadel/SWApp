package com.example.form.Controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.Entities.Brand;
import com.example.form.Entities.Product;
import com.example.form.Entities.Store;
import com.example.form.Entities.Store_Product;
import com.example.form.Repository.BrandRepository;
import com.example.form.Repository.ProductRepsitory;
import com.example.form.Repository.StoreRepository;
import com.example.form.Repository.Store_productRepository;

@Controller
public class CollaboratorController extends StoreOwnerController {
	@Autowired
	StoreRepository StoreRepo ;
	@Autowired 
	ProductRepsitory productRepo;
	@Autowired
	Store_productRepository StoreproductRepo;
	
	
	@Autowired 
	BrandRepository BrandRepo;
	@GetMapping("/StoreOwnerHomePage")
	
	public String StoreOwnerHomePage()
	{
		return "StoreOwnerHomePage";
		
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
	
	
}
