package com.mvc.mobile.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.mobile.bean.LoginBean;
import com.mvc.mobile.bean.ProductBean;
import com.mvc.mobile.service.ProductService;

import jakarta.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	public ProductService productService;
	
	//loading login page
	@GetMapping("/login")
	public String loginForm(Model model) {
		
		return "login";
	}
	
	//Validating login page
		@PostMapping("/login")
		public String loginValidate(@ModelAttribute(value = "login")  LoginBean loginBean ,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
			
			boolean loginflag = productService.validateUser(loginBean.getUsername(), loginBean.getPassword());
			
			System.out.println(loginflag);
			
			if(loginflag) {
				session.setAttribute("loginflag", loginflag);
				model.addAttribute("message", "Login Successfull!!!");
				model.addAttribute("welcomemessage", "Hello "+ loginBean.getUsername() + " , Welcome to Spring MVC Project");
				String rediredtUrl = (String) session.getAttribute("redirectUrl");
				if(rediredtUrl!=null) {
					redirectAttributes.addFlashAttribute("message","Login Successfull!!!");
					return "redirect:"+ rediredtUrl;
				}
				else {
					return "home";
				}
				
				
				
			}
			else {
				model.addAttribute("message", "Please enter valid credentials!!!");
				return "login";
			}
			
		}
		
		@GetMapping("/logout")
		public String logout(Model model,HttpSession session) {
			session.invalidate();
			model.addAttribute("message", "Logout Successfull!!!");
			return "login";
		}
	
	//loading home page
	@GetMapping("/home")
	public String homePage(Model model,HttpSession session) {
		
		Object loginflag =    session.getAttribute("loginflag");
		String flag;
		//flag = Boolean.toString(loginflag);
		System.out.println(loginflag);
		
		if(loginflag != null ) {
			
			model.addAttribute("welcomemessage", "Hello , Welcome to Spring MVC Project");
			return "home";
		}
		else {
			model.addAttribute("message", "Please login First");
			return "login";
		}
		
	}

	
    //Loading product form
	@GetMapping("/addproduct")
	public String loadForm(Model model,HttpSession session) {
		
		Object loginflag =    session.getAttribute("loginflag");
		String flag;
		//flag = Boolean.toString(loginflag);
		//System.out.println(loginflag);
		
		if(loginflag != null ) {
			
			model.addAttribute("product", new ProductBean());
			model.addAttribute("actionUri", "/addproduct");
			model.addAttribute("httpMethod", "post");
			return "addproduct";
		}
		else {
			model.addAttribute("message", "Please login First");
			session.setAttribute("redirectUrl", "/addproduct");
			return "login";
		}
		
		
	}

	@PostMapping("/addproduct")
	public String loadForm(@ModelAttribute ProductBean product, Model model, RedirectAttributes redirectAttributes) {
		// model.addAttribute("product", new ProductBean());

		ProductBean save = productService.addProduct(product);
		redirectAttributes.addFlashAttribute("message", "Your product " + product.getBrand() + " " + product.getModel()
				+ " is saved successfully! ID : " + product.getProductid());

		// model.addAttribute("product", new ProductB;ean());
		// model.addAttribute("message","Your product "+ product.getBrand()+"
		// "+product.getModel()+" is saved successfully!");
		model.addAttribute("isEditMode", false);
		return "redirect:/addproduct";
	}

	@GetMapping("/viewallproducts")
	public String viewProducts(Model model,HttpSession session) {
		
		Object loginflag =    session.getAttribute("loginflag");
		String flag;
		//flag = Boolean.toString(loginflag);
		//System.out.println(loginflag);
		
		if(loginflag != null ) {
			
			List<ProductBean> products = productService.getAllProducts();
			model.addAttribute("products", products);
			return "viewallproducts";
		}
		else {
			model.addAttribute("message", "Please login First");
			session.setAttribute("redirectUrl", "/viewallproducts");
			return "login";
		}
		
         
		

	}
    //Get Edit form
	@GetMapping("/editproduct/{id}")
	public String viewedit(@PathVariable(name = "id") long id, Model model) {

		model.addAttribute("product", productService.getProductByID(id));
		model.addAttribute("message", "Edit product details and submit");
		model.addAttribute("isEditMode", true);
		model.addAttribute("actionUri", "/addproduct");
		model.addAttribute("httpMethod", "post");
		return "addproduct";

	}
    
	//Get view Page
	@GetMapping("/viewproduct/{id}")
	public String vieweproduct(@PathVariable(name = "id") long id, Model model) {

		model.addAttribute("product", productService.getProductByID(id));
		model.addAttribute("message", "Edit product details and submit");
		model.addAttribute("isEditMode", true);
		model.addAttribute("isViewMode", true);
		
		model.addAttribute("actionUri", "/editproduct/"+id);
		model.addAttribute("httpMethod", "get");
		
		return "addproduct";

	}

}
