public class negotiateQuote 
{
		protected int negotiateQID;
		protected int quoteID;
	 	protected String email;

	 	protected double price;
	 	protected String start_time;
	 	protected String end_time;
	 	protected String msg;
	 	protected String date;
	 	
	    //constructors
	    public negotiateQuote() {}
	 
	    public negotiateQuote(int negotiateQID) { this.negotiateQID = negotiateQID;}
	    
	    public negotiateQuote(int quoteID, String email, String msg) {
	    	this.quoteID = quoteID;
	    	this.email = email;
	    	this.msg = msg;
    	}
	    
	    public negotiateQuote(int negotiateQID, int quoteID, String email, double price, String start_time, String end_time, String msg, String date) {
	    	this(quoteID, email, price, start_time, end_time, msg);
	    	this.negotiateQID = negotiateQID;
	    	this.date = date;
	    }
	    
	    
	 
	
	    public negotiateQuote(int quoteID, String email, double price, String start_time, String end_time, String msg) {
	    	this.quoteID = quoteID;
	    	this.email = email;
	    	this.price = price;
	    	this.start_time = start_time;
	    	this.end_time = end_time;
	    	this.msg = msg;
	  
	    }
	    
	   //getter and setter methods
	    
	    public int getNegotiateID() { return negotiateQID;}
	    
	    public void setNegotiateID(int negotiateQID) { this.negotiateQID = negotiateQID; }
	    
	    public int getQuoteID() { return quoteID;}
	    
	    public void setQuoteID(int quoteID) { this.quoteID = quoteID; }
	    
	    public double getPrice() { return price; }
	    
	    public void setPrice(double price) { this.price = price; }
	    
	    public String getStart_time() { return start_time; }
	    
	    public void setStart_time(String start_time) { this.start_time = start_time; }
	    
	    public String getEnd_time() {return end_time; }
	    
	    public void setEnd_time(String end_time) { this.end_time = end_time;}
	    
	    public String getEmail() { return email; }
	    
	    public void setEmail(String email) { this.email = email; }
	    
	    public String getMsg() { return msg; }
	    
	    public void setMsg(String msg) { this.msg = msg; }
	    
	    public String getDate() { return date; }
	    
	    public void setDate(String date) { this.date = date; }
	    
	}