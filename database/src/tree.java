public class tree 
{
		protected int treeID;
	 	protected double width;
	    protected double height;
	    protected String address;
	    protected String city;
	    protected String state;
	    protected String zipcode;
	    protected double distance;
	    protected String image1;
	    protected String image2;
	    protected String image3;
	    protected String notes;
	    protected String date;
	    protected int quoteID;
	 
	    //constructors
	    public tree() {
	    }
	 
	    public tree(int treeID) { this.treeID = treeID; }
	    
	    public tree(int treeID, double width, double height, String address, String city, String state, String zipcode, double distance, String image1, String image2, String image3, String notes, String date, int quoteID) 
	    {
	    	this(width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date, quoteID);
	    	this.treeID = treeID;
	    }
	 
	
	    public tree(double width, double height, String address, String city, String state, String zipcode, double distance, String image1, String image2, String image3, String notes, String date, int quoteID) 
	    {
	    	this.width = width;
	    	this.height = height;
	    	this.address = address;
	    	this.city = city;
	    	this.state = state;
	    	this.zipcode = zipcode;
	    	this.distance = distance;
	    	this.image1 = image1;
	    	this.image2 = image2;
	    	this.image3 = image3;
	    	this.notes = notes;
	    	this.date = date;
	    	this.quoteID = quoteID;

	  
	    }
	    
	   //getter and setter methods
	    public int getTreeID() {
	        return treeID;
	    }
	    public void setTreeID(int treeID) {
	        this.treeID = treeID;
	    }
	    
	    public double getWidth() {
	        return width;
	    }
	    public void setWidth(double width) {
	        this.width = width;
	    }
	    
	    public double getHeight() {
	    	return height;
	    }
	    
	    public void setHeight(double height) {
	    	this.height = height;
	    }

	    public String getAddress() {
	        return address;
	    }
	    public void setAddress(String address) {
	        this.address = address;
	    }
	    
	    public String getCity() {
	        return city;
	    }
	    public void setCity(String city) {
	        this.city = city;
	    }
	    
	    public String getState() {
	        return state;
	    }
	    public void setState(String state) {
	        this.state = state;
	    }
	    
	    public String getZipcode() {
	    	return zipcode;
	    }
	    
	    public void getZipcode(String zipcode) {
	    	this.zipcode = zipcode;
	    }
	    
	    public double getDistance() {
	    	return distance;
	    }
	    
	    public void setDistance(double distance) {
	    	this.distance = distance;
	    }
	    
	    public String getImage1() {
	    	return image1;
	    }
	    
	    public void setImage1(String image1) {
	    	this.image1 = image1;
	    }
	    
	    public String getImage2() {
	    	return image2;
	    }
	    
	    public void setImage2(String image2) {
	    	this.image2 = image2;
	    }
	    
	    public String getImage3() {
	    	return image1;
	    }
	    
	    public void setImage3(String image3) {
	    	this.image3 = image3;
	    }
	    
	    public String getNotes() {
	    	return notes;
	    }
	    
	    public void setNotes(String notes) {
	    	this.notes = notes;
	    }
	    
	    public String getDate() {
	    	return date;
	    }
	    
	    public void setDate(String date) {
	    	this.date = date;
	    }
	    
	    public int getQuoteID() { return quoteID; }
	    
	    public void setQuoteID(int quoteID) { this.quoteID = quoteID; }
	    
	}