package meghana;

public class Items {
	String name;
	int price;
	   int stock;
	
	
	   public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

  
   public Items(String name, int i, int stock) {
	
		this.name = name;
		this.price = i;
		this.stock = stock;
		
	}
   
   public Items() {
   
   }

@Override
public String toString() {
	return "Items [name=" + name + ", price=" + price + ", stock=" + stock + "]";
}
 
}
