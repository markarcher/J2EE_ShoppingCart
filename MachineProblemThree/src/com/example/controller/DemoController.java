package com.example.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.CartOrder;
import com.example.domain.Category;
import com.example.domain.InventoryItem;
import com.example.domain.Product;
import com.example.domain.ProductModel;
import com.example.domain.ShoppingCart;
import com.example.domain.User;
import com.example.service.CartOrderDetailService;
import com.example.service.CartOrderService;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.service.Service;



@Controller
public class DemoController {
	@Autowired
	private Service userService;
	@Autowired
	private CategoryService categoryservice;
	@Autowired
	private ProductService productservice;
	@Autowired
	private CartOrderService cartOrderService;
	@Autowired
	private CartOrderDetailService cartOrderDetailService;
	
	
	@RequestMapping("/login")
	public String anythingReally(){
		
		return "login";
	}

	@RequestMapping("/LogOut")
	public String logout(HttpServletRequest request,HttpSession session){
		session.invalidate();
		return "login";
	}
	
	
	@RequestMapping(value="/Home", method = RequestMethod.POST)
	public String home(Model model,HttpServletRequest request,HttpSession session,
			@RequestParam("userName") String userName,
		 	@RequestParam("password") String password){
		request.getSession(true);
		session.setMaxInactiveInterval(30*60);
		
		String pageMap = "login";
		List<User> users = userService.getUserbyName(userName);
		String username;
		String pword = null;
		int role = 0;
		int userId = 0;
		for(User u : users){
			 username = u.getUserName();
			 pword = u.getPassword();
			 role = u.getRole();
			 userId = u.getId();
			if(userName.equals(username) && password.equals(pword) && role == 2 ){
				session.setAttribute("password", pword);
	      		session.setAttribute("role", role);
	      		session.setAttribute("user",userName);
	      		session.setAttribute("userId",userId);		
				List<Category> categories = categoryservice.getAllCategories();
				model.addAttribute("categoryList",categories);
				pageMap = "customerHome";
			}else if(userName.equals(username) && password.equals(pword) && role == 1){
				session.setAttribute("password", pword);
	      		session.setAttribute("role", role);
	      		session.setAttribute("user",userName);
	      		session.setAttribute("userId",userId);	
	      		model.addAttribute("User", userName);
				pageMap = "adminHome";
			}
		}
				
		return pageMap;
	}
	
	
	@RequestMapping(value="/AddCategoryOrProduct", method= RequestMethod.GET)
	public String demoWithParameter(Model model, @RequestParam("myaction") String myaction){
		String pageMap = null;
		if(myaction.equals("category")){
			List<Category> categories = categoryservice.getAllCategories();
			model.addAttribute("categoryList",categories);
			
		   pageMap = "addCategory";
		}
		if(myaction.equals("product")){
			List<Category> categories = categoryservice.getAllCategories();
			model.addAttribute("categoryList",categories);
			
			List<Product> products = productservice.getAllProducts();
			model.addAttribute("productList", products);
			
			pageMap = "addProduct";
		}
		return pageMap;
	}

	@RequestMapping(value="/AddCategory", method = RequestMethod.GET)
	public String addingCategories(Model model, @RequestParam("catName") String categoryName ){
		List<Category> categories = categoryservice.getAllCategories();
		model.addAttribute("categoryList",categories);
		Category category = new Category();
		category.setCategoryName(categoryName);
		
		boolean categoryExist= false;
		
		for(Category cat : categories){
			if(cat.getCategoryName().equals(categoryName)){
				categoryExist = true;
			}
		}
		
		if(categoryExist == false){
			categoryservice.saveCategory(category);
			model.addAttribute("categoryAdded","Category Added");
			List<Category> categories1 = categoryservice.getAllCategories();
			model.addAttribute("categoryList",categories1);
		}else{
			model.addAttribute("categoryAdded","Category Exist");
		}
	  
	return "addCategory";
	}
	
	
	@RequestMapping("/AdminHome")
	public String adminHome(Model model,HttpServletRequest request,HttpSession session){
		String userName = (String) session.getAttribute("user");
		model.addAttribute("User", userName);
	return "adminHome";
	}

	
	
	@RequestMapping("/AddProduct")
	public String addProduct(Model model){
		List<Category> categories = categoryservice.getAllCategories();
		model.addAttribute("categoryList",categories);
		List<Product> products = productservice.getAllProducts();
		model.addAttribute("productList", products);
		
	return "addProduct";
	}
	
	
	
	@RequestMapping("/UpdateProduct")
	public String updateProduct(Model model,
			@RequestParam("productName") String productName,
			@RequestParam("quantity") int quantity,
			@RequestParam("price") BigDecimal price,
			@RequestParam("categoryName") String categoryName){
		
		List<Product> products = productservice.getAllProducts();
		model.addAttribute("productList", products);
		
		boolean productExist = false;
		for(Product p : products){
			if(p.getProductName().equals(productName)){
				productExist = true;
			}
		}
		
		if(productExist == false){
			
			Category category = categoryservice.getCategoryNo(categoryName);
			ProductModel productModel = new ProductModel(0,productName,price,categoryName);
			int categoryNum = category.getCategoryNo();
			InventoryItem inventory = new InventoryItem(productModel,quantity);
			productservice.addProduct(inventory,categoryNum);
		}else{
			model.addAttribute("resulOfUpdate","Product Already Exist");
		}
		List<Product> products1 = productservice.getAllProducts();
		model.addAttribute("productList", products1);
		return "updatedProduct";
	}
	
	
	
	@RequestMapping("/CustomerHome")
	public String demoWithFormParameter(Model model,HttpServletRequest request,HttpSession session
			){
			String userName = (String) session.getAttribute("user");
			model.addAttribute("User", userName);
			List<Category> categories = categoryservice.getAllCategories();
			model.addAttribute("categoryList",categories);
		return "customerHome";
	}
	
	
	@RequestMapping("/ShowProducts")
	public String showProducts(Model model,HttpServletRequest request,HttpSession session,
			@RequestParam("categoryType") int categoryType){
            
			List<Product> products = productservice.getProducts(categoryType);
			model.addAttribute("productList", products);
		return "shopping";
	}
	
	
	@RequestMapping("/AddToCart")
	public String addToCart(Model model,HttpServletRequest request,HttpSession session,
			@RequestParam("productType") String productName,
			@RequestParam("quantity") int quantity){

		int user = (int) session.getAttribute("userId");
		Product prod;
		prod = productservice.getParticularProduct(productName);
		ProductModel productModel = new ProductModel(prod.getProductId(),prod.getProductName(),prod.getPrice(),"");
		BigDecimal totalSales = null;
		BigDecimal price = prod.getPrice();
		totalSales = price.multiply( new BigDecimal(quantity));
		
		if(prod.getQuantity() > quantity){
			int orderId = cartOrderService.getOrderNumber(user);
			if(orderId == 0){
				CartOrder cartOrder = new CartOrder();
				cartOrder.setUserId(user);
				cartOrder.setTotalSales(totalSales);
				cartOrderService.addToOrder(cartOrder);
			}else{
				cartOrderService.increaseTotalSales(totalSales,user);
				BigDecimal amount;
				amount = cartOrderService.getTotalSales(user);
				model.addAttribute("totalAmount", amount);
			}

			InventoryItem inventory = new InventoryItem(productModel,quantity);
			int orderId1 = cartOrderService.getOrderNumber(user);
				
			cartOrderDetailService.addToOrderDetail(inventory,orderId1);
			ShoppingCart shoppingcart = cartOrderDetailService.getContentsInCart(user,orderId);
			List<InventoryItem> inventories = new ArrayList<>(); 
			if(shoppingcart != null){
				inventories = shoppingcart.getInventoryItem();
				model.addAttribute("shoppingList",inventories);
			}
		}else{
			model.addAttribute("NotEnoughQty", "Not Enough Quanity in Inventory");
		}
	return "redirect:ShowCart";
	}
	
	
	@RequestMapping("/ShowCart")
	public String ShowCart(Model model,HttpServletRequest request,HttpSession session){
		int user = (int) session.getAttribute("userId");
		String userName = (String) session.getAttribute("user");
		int orderId = cartOrderService.getOrderNumber(user);
			 
			ShoppingCart shoppingcart = cartOrderDetailService.getContentsInCart(user,orderId);
			if (shoppingcart == null){
				model.addAttribute("emptyCart","Cart is Empty");
			}else{
			
				List<InventoryItem> inventories = new ArrayList<>();
				inventories = shoppingcart.getInventoryItem();
				model.addAttribute("shoppingList",inventories);
				BigDecimal amount;
				amount = cartOrderService.getTotalSales(user);
				model.addAttribute("totalAmount", amount);
				model.addAttribute("User",userName);
			}
		return  "showCart";
	}
	
	
	@RequestMapping("/ClrRemoveItemsFrmCart")
	public String clearRemoveItems(Model model,HttpServletRequest request,HttpSession session){
		
		String  clear = request.getParameter("clear");
		String remove = request.getParameter("remove");
		String checkOut = request.getParameter("checkout");
		int user = (int) session.getAttribute("userId");
		String userName = (String) session.getAttribute("user");
		String pageMap = null;
		int orderId = 0;
		if(checkOut != null){
			if(checkOut.equals("check_Out")){
				
				orderId = cartOrderService.getOrderNumber(user);
				List<InventoryItem> inventories = new ArrayList<>(); 
				ShoppingCart shoppingcart = cartOrderDetailService.getContentsInCart(user,orderId);
				inventories = shoppingcart.getInventoryItem();
				model.addAttribute("shoppingList",inventories);
				BigDecimal amount = shoppingcart.getTotalAmount();
				model.addAttribute("amount", amount);
				model.addAttribute("User",userName);
				cartOrderService.checkOutItems(orderId);
				productservice.subtractQtyFrmProducts(inventories);
				pageMap = "checkOutItems";
			}
		}
		
		if(clear != null){
			if(clear.equals("clearItems")){
				orderId = cartOrderService.getOrderNumber(user);
				cartOrderDetailService.clearItemsFrmCart(orderId);
				pageMap = "redirect:ShowCart";
			}
		}	
		
		if(remove != null){
			if(remove.equals("removeItem")){
				String productName = request.getParameter("productType");
				orderId = cartOrderService.getOrderNumber(user);
		        cartOrderDetailService.removeOneItem(orderId,productName);
				pageMap = "redirect:ShowCart";
			}
		}
		
	return pageMap;
	}
	
	
}
