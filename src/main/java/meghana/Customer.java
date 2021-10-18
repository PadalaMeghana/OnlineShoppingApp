package meghana;

public class Customer {
	
	
	 private long id;
	
	private String name;
	
	 private String contact;
	 
	    
	 public long getId() {
	        return id;
	    }
	    public void setId(long id) {
	        this.id = id;
	    }
	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	 public Customer(String name, String contact) {
		
		this.name = name;
		this.contact = contact;
		
	}
	 public Customer() {
		 
	 }
	
}
