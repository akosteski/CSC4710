public class negotiateBill 
{
		protected int quoteID;
	 	protected double price;
	 	protected String start_time;
	 	protected String end_time;
	 	protected String status;
	 	protected String email;
	 
	    //constructors
	    public negotiateBill() {}
	 
	    public negotiateBill(int quoteID) { this.quoteID = quoteID;}
	    
	    public negotiateBill(int quoteID, double price, String start_time, String end_time, String status, String email) {
	    	this(price, start_time, end_time, status, email);
	    	this.quoteID = quoteID;
	    }
	 
	
	    public negotiateBill(double price, String start_time, String end_time, String status, String email) {
	    	this.price = price;
	    	this.start_time = start_time;
	    	this.end_time = end_time;
	    	this.status = status;
	    	this.email = email;

	  
	    }
	    
	   //getter and setter methods
	    public int getQuoteID() { return quoteID;}
	    
	    public void setQuoteID(int quoteID) { this.quoteID = quoteID; }
	    
	    public double getPrice() { return price; }
	    
	    public void setPrice(double price) { this.price = price; }
	    
	    public String getStart_time() { return start_time; }
	    
	    public void setStart_time(String start_time) { this.start_time = start_time; }
	    
	    public String getEnd_time() {return end_time; }
	    
	    public void setEnd_time(String end_time) { this.end_time = end_time;}
	    
	    public String getStatus() { return status; }
	    
	    public void setStatus(String status) { this.status = status; }
	    
	    public String getEmail() { return email; }
	    
	    public void setEmail(String email) { this.email = email; }
	    
	}