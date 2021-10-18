package meghana;

import javax.annotation.PostConstruct;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/shopping")
public class HomeController {

    ShoppingCart shoppingCart=new ShoppingCart("more");
    Items i1=new Items("chocolate",50,100);
    Items i2=new Items("chips",10,150);
    Items i3=new Items( "milk",15,200);
    Items i4=new Items("shampoo",300,80);
    Items i5=new Items("soap",100,60);
    
	@PostConstruct
	public void addcustomers() {
		
	
        shoppingCart.add_Items(i1);
        shoppingCart.add_Items(i2);
		shoppingCart.add_Items(i3);
		shoppingCart.add_Items(i4);
		shoppingCart.add_Items(i5);
		
	}
	//home page
	@GetMapping("/init")
	 public String homepage(Model m){
		 return "shopowner";
	 }
	//login page
	@GetMapping("/login")
	public String getLoginForm() {
		return "login";
	}
	//checking for login credentials
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute(name="loginForm")Login loginForm,Model m) {
		String username=loginForm.getUsername();
		String password=loginForm.getPassword();
		if("admin".equals(username) && "admin".equals(password)) {
			return "adminpage";
		}
		m.addAttribute("invalidCredentials",true);
		return "login";
	}
	
	//register page
	@GetMapping("/cpage")
	 public String customerpage(Model m){
		
		 return "home";
	 }
	//customer details page
	@GetMapping("/cdpage")
	 public String customerdetailspage(Model m){
		
		m.addAttribute("customers",shoppingCart.getCustomers());
		 return "custdetails";
	 }
	//for shopping
	
	@GetMapping("/shop")
	public String cust_items(@RequestParam("customer") String name,Model m) {
		int p=shoppingCart.check_cust(name);
		if(p>=0) {
			m.addAttribute("customer",name);
			m.addAttribute("items",shoppingCart.getItems());
			return "cust-list";
			
		}
		return "redirect:init";
	}


	  //adding customer
	  
	  @GetMapping("/addcustomer") 
	  public String add_cust(Model m) {
	  m.addAttribute("customer",new Customer());
	   return "cust-form";
	  
	  }
	  
	  @PostMapping("/savecust")
	  public String save_cust(@ModelAttribute("customer")Customer customer) { 
		 if(customer.getName()!=null && customer.getContact()!=null) { 
			 int p=shoppingCart.check_cust(customer.getName()); 
			 Customer tosave=null; 
			 if(p>=0) { 
				 tosave=shoppingCart.getCustomers().get(p);
				 tosave.setName(customer.getName());
				 tosave.setContact(customer.getContact());
				 return "redirect:cdpage"; 
				 }else {
					 tosave=customer;
					 shoppingCart.add_customer(tosave);
	  
	  }
	  
	  } 
		 return "redirect:cdpage";
		 } 
	  //updating customer
	  
	  @GetMapping("/updcust") 
	  public String upd_cust(@RequestParam("customer")String name,Model m) {
		  if(name!=null) { 
			  int p=shoppingCart.check_cust(name); 
			  if(p>=0) { 
				  Customer customer=shoppingCart.getCustomers().get(p);
	  m.addAttribute("customer",customer);
	  return "cust-update"; }
			  }
		  return "init";
	  }
	  
	  @PostMapping("/updatecust")
	  public String savecust(@RequestParam("name")String[] name,@ModelAttribute("cutomer")Customer customer) {
	  if(customer.getName()!=null && customer.getContact()!=null) { 
		  int p=shoppingCart.check_cust(name[0]);
		  if(p>=0) {
			  Customer customer1=shoppingCart.getCustomers().get(p); 
			  customer1.setName(name[1]);
			  customer1.setContact(customer.getContact()); 
			  return "redirect:cdpage"; 
			  } 
		  }
	  return "redirect:cdpage"; 
	  }
	  
	  //deleting customer
	  
	  @GetMapping("/delcust") 
	  public String del_cust(@RequestParam("customer")String name) {
		  if(name!=null) {
			  int p=shoppingCart.check_cust(name); 
			  if(p>=0) {
	  shoppingCart.getCustomers().remove(p);
	  } 
			  }
		  return "redirect:init";
		  }
	 
		
	//adding items
	
	@GetMapping("/items")
	public String listitems(Model m) {
		m.addAttribute("Items",shoppingCart.getItems());
		return "items";
	}
	
	@GetMapping("/additems")
	public String add_item(Model m) {
		m.addAttribute("Item",new Items());
		return "item-form";
		
	}
	@PostMapping("saveitem")
	public String save_item(@ModelAttribute("Item") Items item) {
		
		if((item.getName()!=null && item.getPrice()!=0) && ( item.getStock()!=0)) {
			int p=shoppingCart.check_item(item.getName());
			System.out.println(p);
			if(p>=0) {
				Items items=shoppingCart.getItems().get(p);
				items.setName(item.getName().trim());
				items.setPrice(item.getPrice());
				items.setStock(item.getStock());
				return "redirect:items";
				
			}else {
				shoppingCart.add_Items(item);
				
			}
		}
	
		return "redirect:items";
	}
	//updating items
	@PostMapping("updateitem")
	public String update_item(@RequestParam("name")String[] name,@ModelAttribute("Item")Items item) {
		if((item.getName()!=null && item.getPrice()!=0) && ( item.getStock()!=0)) {
			int p=shoppingCart.check_item(name[0]);
			System.out.println(p+" =>"+name[0]);
			if(p>=0) {
				Items items=shoppingCart.getItems().get(p);
				items.setName(name[1]);
				items.setPrice(item.getPrice());
				items.setStock(item.getStock());
				System.out.println(item.toString());
				return "redirect:items";
				
			}else {
				shoppingCart.add_Items(item);
				
			}
		}
		return "redirect:items";
	
	}
	
	@GetMapping("/itemupd")
	public String upd_item(@RequestParam("name")String name,Model m) {
		if(name!=null) {
			int p=shoppingCart.check_item(name);
			if(p>=0) {
				Items item=shoppingCart.getItems().get(p);
				m.addAttribute("Item",item);
				return "item-update";
			}
		}
		return "redirect:items";
	}
	//delete items
	@GetMapping("/itemdel")
	public String del_item(@RequestParam("name")String name) {
		if(name!=null) {
			int p=shoppingCart.check_item(name);
			if(p>=0) {
				
			    shoppingCart.getItems().remove(p);
				
			}
		}
		return "redirect:items";
	}
	
	//adding to cart
	
	
    @GetMapping("/newitems")
    public String listitem(Model m) {
    m.addAttribute("Items",shoppingCart.getItems());
    return "new-item";
}
    
    @GetMapping("/custitem") 
	public String add_items(@RequestParam("name")String name,Model m) {
		if(name!=null) {
			int p=shoppingCart.check_item(name);
			if(p>=0) {
				Items item=shoppingCart.getItems().get(p);
				m.addAttribute("Item",item);
				return "add-cust-item";
			}
		}
		return "add-cust-item";
	}
    
    @PostMapping("additem")
	public String ADD_item(@RequestParam("name")String[] name,@ModelAttribute("Item")Items item) {
		if((item.getName()!=null && item.getPrice()!=0) && ( item.getStock()!=0)) {
			int p=shoppingCart.check_item(name[0]);
			
			if(p>=0) {
				Items items=shoppingCart.getItems().get(p);
				items.setName(name[1]);
				items.setPrice(item.getPrice());
				items.setStock(item.getStock());
				
				return "redirect:newitems";
				
			}
		}
		return "redirect:newitems";
	
	}
	
	
	@GetMapping("/removeitem")
	public String remitem(@RequestParam("name")String name) {
		if(name!=null) {
			int p=shoppingCart.check_item(name);
			if(p>=0) {
			    shoppingCart.getItems().remove(p);
			}
		}
		return "redirect:newitems";
	}
	

}
