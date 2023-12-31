import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO;
	    private quoteDAO quoteDAO;
	    private treeDAO treeDAO;
	    private negotiateQuoteDAO negotiateQuoteDAO;
	    private String currentUser;
	    private int currentQuote;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	try {
		    	quoteDAO = new quoteDAO(dbConnect.getDbConnection());
		    	userDAO = new userDAO(dbConnect.getDbConnection());
		    	treeDAO = new treeDAO(dbConnect.getDbConnection());
		    	negotiateQuoteDAO = new negotiateQuoteDAO(dbConnect.getDbConnection());
		    	currentUser= "";
		    	currentQuote = 0;
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		quoteDAO.init(); //quote table
        		treeDAO.init(); //tree table
        		negotiateQuoteDAO.init(); //this one's pretty self explanatory
        		negotiateBillDao.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"initialize");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	case "/list": 
                 System.out.println("The action is: list");
                 listAll(request, response);           	
                 break;
        	case "/request":
        		System.out.println("The action is: request");
        		requestQuoteStart(request, response);
        		break;
        		
        	case "/generateQuote":
        		System.out.println("generating a quote");
        		generateQuote(request, response);
        		break;
        		
        	case "/makeTree":
        		System.out.println("The action is: make a tree");
        		makeTree(request, response);
        		break;
        		
        	case "/createTree":
        		System.out.println("The action is: creating a tree");
        		createTree(request, response);
        		break;
        		
        	case "/selectRequest":
        		System.out.println("request has been selected");
        		selectReq(request, response);
        		break;
        		
        	case "/selectQuote":
        		System.out.println("quote has been selected");
        		selectQuote(request, response);
        		break;
    		
        	case "/sendRequest":
        		System.out.println("Sending request...");
        		//sendQuoteRequest(request, response);
        		break;
        		
        	case "/viewQuotes":
        		System.out.println("Showing all quotes for David Smith...");
        		listAllQuotes(request, response);
        		break;
        	
        	case "/viewQuotesUser":
        		System.out.println("Showing all quotes for the user");
        		listUserQuotes(request, response);
        		break;
        		
        	case "/viewMessages":
        		System.out.println("Showing messages for this quote request");
        		viewMessages(request, response);
        		break;
        		
        	case "/sendFirstResponse":
        		System.out.println("Sending an initial response");
        		sendInitResponse(request, response);
        		break;
        		
        	case "/sendResponse":
        		System.out.println("Sending response...");
        		sendResponse(request, response);
        		break;
        	
        	case "/msgGoBack":
        		System.out.println("Gotta have this ugh");
        		msgGoBack(request, response);
        		break;
        		
        	case "/finalizeRequest":
        		System.out.println("Going to finalize this request");
        		reqFinal(request,response);
        		break;
                 
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listAll(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	        
	        List<quote> listQuote = quoteDAO.listAllQuotes();
	        request.setAttribute("listQuote", listQuote);
	        RequestDispatcher dispatcher2 = request.getRequestDispatcher("QuoteList.jsp");
	        dispatcher2.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
			request.setAttribute("listQuote", quoteDAO.listAllQuotes());
			request.setAttribute("listTree", treeDAO.listAllTrees());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 
	    	 else if (email.equals("davidsmith@gmail.com") && password.equals("dsmith"))
	    	 {
				 System.out.println("Login Successful! Redirecting to David Smith Page");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 currentUser = email;
				 System.out.println("The current user is: " + currentUser);
				 request.getRequestDispatcher("DavidSmithview.jsp").forward(request, response);
	
	    	 }
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 System.out.println("The current user is: " + currentUser);
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String birthday = request.getParameter("birthday");
	   	 	String adress_street_num = request.getParameter("adress_street_num"); 
	   	 	String adress_street = request.getParameter("adress_street"); 
	   	 	String adress_city = request.getParameter("adress_city"); 
	   	 	String adress_state = request.getParameter("adress_state"); 
	   	 	String adress_zip_code = request.getParameter("adress_zip_code");
	   	 	String credit = request.getParameter("credit");
	   	 	String phone = request.getParameter("phone");
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email, firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, credit, phone);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
	    	currentQuote = 0;
	    	
	    	System.out.println("Current user is: " + currentUser + " Current quote ID is: " + currentQuote);
	    	response.sendRedirect("login.jsp");
        	}
	    
	    private void requestQuoteStart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	//int tree_amt = Integer.parseInt(request.getParameter("tree_amt"));
	    	//double price = Double.parseDouble(request.getParameter("price"));
	    	//String start_date = request.getParameter("start_date");
	    	//String end_date = request.getParameter("end_date");
	    	//String status = request.getParameter("status");
	    	String email = currentUser;
	    	int quoteID = currentQuote;
	    	System.out.println("The current user is: " + email);
	    	
	    	request.setAttribute("listUserQuote", quoteDAO.listUserQuotes(email));
	    	    	
	    	if (quoteID > 0) {
	    		System.out.println("Displaying trees under quote " + quoteID);
		    	request.setAttribute("listQuoteTree", treeDAO.listQuoteTrees(quoteID));
		    	request.setAttribute("QuoteID", quoteID);
	    	}
	    	
	    	else {
	    		System.out.println("No quoteID has been selected yet!");
	    	}
	    	request.getRequestDispatcher("RequestQuote.jsp").forward(request, response);
	    }
	    
	    private void selectQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoteID = Integer.parseInt(request.getParameter("quoteID"));
	    	String email = currentUser;
	    	
	    	currentQuote = quoteID;
	    	System.out.println("Displaying trees under quote " + quoteID);
	    	request.setAttribute("listQuoteTree", treeDAO.listQuoteTrees(quoteID));
	    	request.setAttribute("QuoteID", quoteID);
	    	
	    	if (email.equals("davidsmith@gmail.com")) {
		    	request.getRequestDispatcher("viewQuoteTrees.jsp").forward(request, response);
	    	}
	    	
	    	else { 
	    		request.getRequestDispatcher("userViewQuoteTrees.jsp").forward(request, response);
	    		
	    	}
	    	
	    }
	    
	    private void selectReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoteID = Integer.parseInt(request.getParameter("quoteID"));
	    	//String email = currentUser;
	    	
	    	currentQuote = quoteID;
	    	
	    	requestQuoteStart(request, response);
	    	
	    }
	    
	    private void generateQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	
	    	quote quotes = new quote(email);
	    	quoteDAO.insert(quotes);
	    	
	    	requestQuoteStart(request, response);
	    	
	    }
	    
	    private void makeTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	
	    	int quoteID = currentQuote;
	    	
	    	System.out.println("The current user is: " + email);
	    	
			System.out.println("The current quote ID is: " + quoteID);
			
			request.getRequestDispatcher("Tree.jsp").forward(request, response);
	    }

	    
	    private void createTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoteID = currentQuote;
			System.out.println("The current quote ID is: " + quoteID + ", under the user: " + currentUser);


	    	double width = Double.parseDouble(request.getParameter("width"));
            double height = Double.parseDouble(request.getParameter("height"));
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state"); 
            String zipcode = request.getParameter("zipcode"); 
            double distance = Double.parseDouble(request.getParameter("distance"));
            String image1 = request.getParameter("image1");
            String image2 = request.getParameter("image2");
            String image3 = request.getParameter("image3");
            String notes = request.getParameter("notes"); 
            String date = request.getParameter("date");            
           
            System.out.println("Sending info to the Tree");
            tree trees = new tree(width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date, quoteID);
            treeDAO.insert(trees);
            treeDAO.countTree(quoteID);
            //treeDAO.update(trees);
            
            requestQuoteStart(request, response);
			//request.getRequestDispatcher("RequestQuote.jsp").forward(request, response);
	    }
	    
	    private void listAllQuotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			request.setAttribute("listQuote", quoteDAO.listAllQuotes());
			
	    	request.getRequestDispatcher("davidViewQuotes.jsp").forward(request, response);
	    }
	    
	    private void listUserQuotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	
	    	request.setAttribute("listUserQuote", quoteDAO.listUserQuotes(email));
	    	
	    	request.getRequestDispatcher("userViewQuotes.jsp").forward(request, response);
	    }
	    
	    private void msgGoBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = currentUser;
	    	
	    	if (email.equals("davidsmith@gmail.com")) {
	    		listAllQuotes(request, response);
	    	}
	    	
	    	else {
	    		listUserQuotes(request, response);
	    	}
	    }
	    
	    private void sendInitResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoteID = currentQuote;
	    	String email = currentUser;
	    	String status = request.getParameter("status");
	    	String msg = request.getParameter("msg");
	    	
	    	if (status.equals("Rejected")) {
	    		System.out.println(currentUser);
	    		System.out.println("This quote has been rejected");
	    		negotiateQuote reject = new negotiateQuote(quoteID, email, msg);
	    		negotiateQuoteDAO.insert(reject);
	    	}
	    	
	    	else {
	    		System.out.println("This quote has been accepted");

	    		double price = Double.parseDouble(request.getParameter("price"));
		    	String start_time = request.getParameter("start_time");
		    	String end_time = request.getParameter("end_time");
		    	//String date = request.getParameter("date");
		    	
		    	negotiateQuote negQs = new negotiateQuote(quoteID, email, price, start_time, end_time, msg);
		    	negotiateQuoteDAO.insert(negQs);
		    	
		    	quote quotes = new quote(quoteID, price, start_time, end_time, status);
		    	quoteDAO.update(quotes);
	    	}
	    	
	    	System.out.println("Updating quote status");
	    	quoteDAO.updateStatus(quoteID, status);
	    	
	    	listAllQuotes(request, response);
	    }
	    
	    private void sendResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoteID = currentQuote;
	    	String email = currentUser;
	    	//String status// = request.getParameter("status");
	    	String msg = request.getParameter("msg");
	    	

    		negotiateQuote respond = new negotiateQuote(quoteID, email, msg);
    		negotiateQuoteDAO.insert(respond);
	    	
	    	
	    	viewMessages(request, response);
	    }
	    
	    private void viewMessages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoteID = currentQuote;
	    	
	    	request.setAttribute("QuoteID", quoteID);
	    	request.setAttribute("listMessage", negotiateQuoteDAO.listConvo(quoteID));
	    	
	    	request.getRequestDispatcher("messageBoard.jsp").forward(request, response);
	    	
	    }
	    
	    private void reqFinal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoteID = currentQuote;
	    	
	    	negotiateQuoteDAO.delete(quoteID);
	    	treeDAO.delete(quoteID);
	    	quoteDAO.delete(quoteID);
	    	
	    	listUserQuotes(request, response);
	    }
	    
}
	        
	        
	    
	        
	        
	        
	    


