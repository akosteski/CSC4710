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
@WebServlet("/quoteDAO")
public class quoteDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public quoteDAO(Connection dbInstance) { connect = dbInstance; }
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    
    public List<quote> listAllQuotes() throws SQLException {
        List<quote> listQuote = new ArrayList<quote>();        
        String sql = "SELECT * FROM Quote";      
              
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int quoteID = resultSet.getInt("quoteID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String email = resultSet.getString("email");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
             
            quote quotes = new quote(quoteID, tree_amt, price, start_time, end_time, status, email);
            listQuote.add(quotes);
            
        }        
        resultSet.close();
        return listQuote;
    }
    
    public List<quote> listUserQuotes(String email) throws SQLException {
        List<quote> listUserQuote = new ArrayList<quote>();        
        String sql = "SELECT * FROM Quote WHERE email=?";      
              
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int quoteID = resultSet.getInt("quoteID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            
            quote quotes = new quote(quoteID, tree_amt, price, start_time, end_time, status, email);
            listUserQuote.add(quotes);
            
        }        
        resultSet.close();
        return listUserQuote;
    }
    
    public List<quote> listPendingQuotes(String email) throws SQLException {
        List<quote> listPendingQuote = new ArrayList<quote>();        
        String sql = "SELECT * FROM Quote WHERE email=? and status='Pending'";      
              
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int quoteID = resultSet.getInt("quoteID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            
            quote quotes = new quote(quoteID, tree_amt, price, start_time, end_time, status, email);
            listPendingQuote.add(quotes);
            
        }        
        resultSet.close();
        return listPendingQuote;
    }
    
    public List<quote> listRejectedQuotes(String email) throws SQLException {
        List<quote> listRejectedQuote = new ArrayList<quote>();        
        String sql = "SELECT * FROM Quote WHERE email=? and status='Rejected'";      
              
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int quoteID = resultSet.getInt("quoteID");
            int tree_amt = resultSet.getInt("tree_amt");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            
            quote quotes = new quote(quoteID, tree_amt, price, start_time, end_time, status, email);
            listRejectedQuote.add(quotes);
            
        }        
        resultSet.close();
        return listRejectedQuote;
    }
    
    public void insert(quote quotes) throws SQLException {
    	            
		String sql = "insert into Quote(email) values (?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			//preparedStatement.setDouble(1, quotes.getPrice());
			//preparedStatement.setString(2, quotes.getStart_time());
			//preparedStatement.setString(3, quotes.getEnd_time());
			//preparedStatement.setString(4, quotes.getStatus());		
			preparedStatement.setString(1, quotes.getEmail());		


		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(int quoteID) throws SQLException {
        String sql = "DELETE * FROM Quote WHERE quoteID = ?";        
        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(quote quotes) throws SQLException {
        String sql = "update Quote set tree_amt=?, price=?, start_time=?, end_time=?, status=?, where quoteID=?";
        
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quotes.getQuoteID());
        preparedStatement.setInt(2, quotes.getTree_amt());
		preparedStatement.setDouble(3, quotes.getPrice());
		preparedStatement.setString(4, quotes.getStart_time());
		preparedStatement.setString(5, quotes.getEnd_time());
		preparedStatement.setString(6, quotes.getStatus());
		preparedStatement.setString(7, quotes.getEmail());		
	
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public quote getQuote(int quoteID) throws SQLException {
    	quote currentQuote = null;
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	int tree_amt = resultSet.getInt("tree_amt");
        	double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time");
            String status = resultSet.getString("status");
            String email = resultSet.getString("email");

            currentQuote = new quote(quoteID, tree_amt, price, start_time, end_time, status, email);
        }
         
        resultSet.close();
        statement.close();
         
        return currentQuote;
    }
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = { "drop table if exists Quote; ",
					        ("CREATE TABLE if not exists Quote( " +
					            "quoteID INTEGER NOT NULL AUTO_INCREMENT, " +
					        	"tree_amt INTEGER DEFAULT 0, " +
					        	"price DECIMAL(5,2) DEFAULT 0, " +
					        	"email VARCHAR(50) NOT NULL, " +
					        	"contractor VARCHAR(50) DEFAULT 'davidsmith@gmail.com', " +
					            "start_time DATE DEFAULT '1999-12-31', " +
					            "end_time DATE DEFAULT '1999-12-31', " +
					            "status VARCHAR(10) DEFAULT 'Pending', " +
					            "PRIMARY KEY (quoteID), " +
					            "FOREIGN KEY (email) REFERENCES User(email)," +
					            "FOREIGN KEY (contractor) REFERENCES User(email));" )
					        
        					};
        String[] TUPLES = {("insert into Quote(quoteID, tree_amt, price, start_time, end_time, status, email)" +
        					"values (414, default, default, '2023-10-10', '2023-10-11', default, 'rey@gmail.com'), " +
        					"(28, default, default, '2023-11-10', '2023-11-11', default, 'j@gmail.com'), " +
        					"(3, default, default, '2023-12-10', '2023-12-11', default, 'wallace@gmail.com'), " +
        					"(1, default, default, '2023-01-10', '2023-02-11', default, 'sophie@gmail.com');"
        					
    		)
		};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        
    }
    
    
   
    
    
    
    
    
	
	

}
