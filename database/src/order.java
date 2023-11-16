public class order 
{
		protected int quoteID;
		protected int tree_amt;
	 	protected double price;
	 	protected String start_time;
	 	protected String end_time;
	 	protected String status;
	 	protected String email;
	 	protected String contractor; //since david smith is the sole contractor, this data entry will NOT be included in any tables, this is just to keep track who is negotiating with who
	 
	    //constructors
	    public order() {}
	 
	    public order(int quoteID) { this.quoteID = quoteID;}
	    
	    public order(String email) {this.email = email; }
	    
	    public order(String status, int quoteID) {
	    	this.status = status;
	    	this.quoteID = quoteID;
	    }
		    
	    public order(int quoteID, int tree_amt, double price, String start_time, String end_time, String status, String email) {
	    	this(tree_amt, price, start_time, end_time, status, email);
	    	this.quoteID = quoteID;
	    }
	    
	    public order(int quoteID, double price, String start_time, String end_time, String status) {
	    	this.price = price;
	    	this.start_time = start_time;
	    	this.end_time = end_time;
	    	this.status = status;
	    	this.quoteID = quoteID;
	    }
	 
	
	    public order(int tree_amt, double price, String start_time, String end_time, String status, String email) {
	    	this.tree_amt = tree_amt;
	    	this.price = price;
	    	this.start_time = start_time;
	    	this.end_time = end_time;
	    	this.status = status;
	    	this.email = email;
	    }
	    
	   //getter and setter methods
	    public int getQuoteID() { return quoteID; }
	    
	    public void setQuoteID(int quoteID) { this.quoteID = quoteID; }
	    
	    public int getTree_amt() { return tree_amt; }
	    
	    public void setTree_amt(int tree_amt) { this.tree_amt = tree_amt; }
	    
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
	    
	    public String getContractor() { return contractor; }
	    
	    public void setContractor(String contractor) { this.contractor = contractor; }
	    
	    
	    
	}