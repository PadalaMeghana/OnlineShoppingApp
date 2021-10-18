package meghana;
import java.util.*;

public class ShoppingCart {
	
	String shop_name;
	List<Items> items;
	List<Customer> customers;
	
	public String getShop_name() {
		return shop_name;
	}

	public List<Items> getItems() {
		return items;
	}

	public List<Customer> getCustomers() {
		return customers;
	}


	public ShoppingCart(String shop_name) {
		this.shop_name=shop_name;
		this.customers=new ArrayList<>();
		this.items=new ArrayList<>();
		
	}
	
	public boolean add_Items(Items item) {
		int pos=check_item(item.getName());
		if(pos<0) {
			this.items.add(item);
			return true;
		}
		else {
			System.out.println("Item => "+item.getName()+" already exist");
			
		}
		return false;
	}
	
	public int check_item(String item) {
		int i=0;
		for(Items item1:this.items) {
			if(item1.getName().trim().toLowerCase().equals(item.trim().toLowerCase()))
			{
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public boolean add_customer(Customer customer) {
		int p=check_cust(customer.getName());
		if(p<0) {
			this.customers.add(customer);
			return true;
		}
		else
		{
			System.out.println("Customer"+customer.getName()+"alreadt exists");
		}
		return false;
	}
	
	public int check_cust(String customer) {
		int i=0;
		for(Customer cust:this.customers) {
			if(cust.getName().toLowerCase().equals(customer.toLowerCase())) {
				return i;
			}
			i++;
		}
		return -1;
	}
	

}
