import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
public class quoteDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public quoteDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<quote> listAllQuotes() throws SQLException {
        List<quote> listQuote = new ArrayList<quote>();        
        String sql = "SELECT * FROM Quote";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int quoteID = resultSet.getInt("quoteID");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            String email = resultSet.getString("email");
             
            quote quotes = new quote(quoteID, price, start_time, end_time, status, email);
            listQuote.add(quotes);
            
        }        
        resultSet.close();
        disconnect();        
        return listQuote;
    }
    
    public List<quote> listPendingQuotes() throws SQLException {
        List<quote> listPendingQuote = new ArrayList<quote>();        
        String sql = "SELECT * FROM Quote WHERE Status = 'Pending'";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int quoteID = resultSet.getInt("quoteID");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            String email = resultSet.getString("email");
             
            quote quotes = new quote(quoteID, price, start_time, end_time, status, email);
            listPendingQuote.add(quotes);
            
        }        
        resultSet.close();
        disconnect();        
        return listPendingQuote;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(quote quotes) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into Quote(price, start_time, end_time, status, email) values (?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setDouble(1, quotes.getPrice());
			preparedStatement.setString(2, quotes.getStart_time());
			preparedStatement.setString(3, quotes.getEnd_time());
			preparedStatement.setString(4, quotes.getStatus());		
			preparedStatement.setString(5, quotes.getEmail());		


		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(int quoteID) throws SQLException {
        String sql = "DELETE FROM Quote WHERE quoteID = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(quote quotes) throws SQLException {
        String sql = "update Quote set price=?, start_time=?, end_time=?, status=?, where quoteID=?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quotes.getQuoteID());
		preparedStatement.setDouble(2, quotes.getPrice());
		preparedStatement.setString(3, quotes.getStart_time());
		preparedStatement.setString(4, quotes.getEnd_time());
		preparedStatement.setString(5, quotes.getStatus());
		preparedStatement.setString(6, quotes.getEmail());		
	
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public quote getQuote(int quoteID) throws SQLException {
    	quote currentQuote = null;
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            String email = resultSet.getString("email");

            currentQuote = new quote(quoteID, price, start_time, end_time, status, email);
        }
         
        resultSet.close();
        statement.close();
         
        return currentQuote;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Quote WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = { "drop table if exists Quote; ",
					        ("CREATE TABLE if not exists Quote( " +
					            "quoteID INTEGER NOT NULL AUTO_INCREMENT, " +
					        	"price DECIMAL(5,2) DEFAULT 100, " +
					        	"email VARCHAR(50) NOT NULL, " +
					            "start_time DATE NOT NULL, " +
					            "end_time DATE NOT NULL, " +
					            "status VARCHAR(10) DEFAULT 'Pending', " +
					            "PRIMARY KEY (quoteID), "+
					            "FOREIGN KEY (email) REFERENCES User(email)); ")
					        
        					};
        String[] TUPLES = {("insert into Quote(quoteID, price, start_time, end_time, status, email)" +
        					"values (414, default, '2023-10-10', '2023-10-11', default, 'rey@gmail.com' );"
        					
    		)
		};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}