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
@WebServlet("/negotiateBillDAO")
public class negotiateBillDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public negotiateBillDAO(Connection dbInstance) { connect = dbInstance; }
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    
    public List<negotiateQuote> listAllNQ() throws SQLException {
        List<negotiateQuote> listNegQ = new ArrayList<negotiateQuote>();        
        String sql = "SELECT * FROM NegotiateQuote";   
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int negotiateBID = resultSet.getInt("negotiateBID");
            int quoteID = resultSet.getInt("quoteID");
            String email = resultSet.getString("email");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time"); 
            String msg = resultSet.getString("msg"); 
            String date = resultSet.getString("date"); 

            negotiateQuote negQs = new negotiateQuote(negotiateBID, quoteID, email, price, start_time, end_time, msg, date);
            listNegQ.add(negQs);
            
        }        
        resultSet.close();
        
                
        return listNegQ;
    }
    
    public List<negotiateQuote> listConvo(int quoteID) throws SQLException {
        List<negotiateQuote> listConvo = new ArrayList<negotiateQuote>();        
        String sql = "SELECT * FROM NegotiateQuote where quoteID=?";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int negotiateBID = resultSet.getInt("negotiateBID");
            String email = resultSet.getString("email");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time"); 
            String msg = resultSet.getString("msg"); 
            String date = resultSet.getString("date"); 

            negotiateQuote quoteConvo = new negotiateQuote(negotiateBID, quoteID, email, price, start_time, end_time, msg, date);
            listConvo.add(quoteConvo);
            
        }        
        resultSet.close();
        
                
        return listConvo;
    }
    
    public void insert(negotiateQuote negQs) throws SQLException {
    	
		String sql = "insert into NegotiateQuote(quoteID, email, price, start_time, end_time, msg) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			//preparedStatement.setInt(1, negQs.getNegotiateID());
			preparedStatement.setInt(1, negQs.getQuoteID());
			preparedStatement.setString(2, negQs.getEmail());
			preparedStatement.setDouble(3, negQs.getPrice());
			preparedStatement.setString(4, negQs.getStart_time());
			preparedStatement.setString(5, negQs.getEnd_time());		
			preparedStatement.setString(6, negQs.getMsg());		
			//preparedStatement.setString(8, negQs.getDate());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(int quoteID) throws SQLException {
        String sql = "DELETE FROM NegotiateQuote WHERE quoteID = ?";        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(negotiateQuote negQs) throws SQLException {
        String sql = "update NegotiateQuote set price=?, start_time=?, end_time= ?, msg=?, date=? where negotiateBID= ?";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, negQs.getNegotiateID());
		preparedStatement.setInt(2, negQs.getQuoteID());
		preparedStatement.setString(3, negQs.getEmail());
		preparedStatement.setDouble(4, negQs.getPrice());
		preparedStatement.setString(5, negQs.getStart_time());
		preparedStatement.setString(6, negQs.getEnd_time());		
		preparedStatement.setString(7, negQs.getMsg());		
		preparedStatement.setString(8, negQs.getDate());		

         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = { "drop table if exists NegotiateBill; ",
		        ("CREATE TABLE if not exists NegotiateBill( " +
		        	"negotiateBID INTEGER NOT NULL AUTO_INCREMENT, " +
		            "orderID INTEGER NOT NULL, " +
		        	"email VARCHAR(50) NOT NULL, " +
		        	"price DECIMAL(7,2) DEFAULT 0, " +
		            "start_time DATE DEFAULT '0001-01-01', " +
		            "end_time DATE DEFAULT '0001-01-01', " +
		            "msg VARCHAR(500)," +
		            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
		            "PRIMARY KEY (negotiateBID), " +
		            "FOREIGN KEY (orderID) REFERENCES Order(orderID)," +
		            "FOREIGN KEY (email) REFERENCES User(email));" )
		        
				};
        String[] TUPLES = {("insert into NegotiateQuote(quoteID, email, price, start_time, end_time, msg, date)"+
        			"values (4, 'davidsmith@gmail.com', default, default, default, 'I am not cutting these trees!', default), " +
        			"(4, 'amelia@gmail.com', default, default, default, 'Please cut my trees!', default), " +
        			"(4, 'davidsmith@gmail.com', default, default, default, 'No', default), " +
        			"(4, 'amelia@gmail.com', default, default, default, 'Please cut my trees!', default), " +
        			"(4, 'davidsmith@gmail.com', default, default, default, 'I already said no.', default), " +
        			"(4, 'amelia@gmail.com', default, default, default, 'Please cut my trees!', default), " +
        			"(4, 'davidsmith@gmail.com', default, default, default, 'NO', default), " +
        			"(4, 'amelia@gmail.com', default, default, default, 'Please cut my trees!', default), " +
        			"(4, 'davidsmith@gmail.com', default, default, default, 'FINE! I will cut your trees', default), " +
        			"(4, 'amelia@gmail.com', default, default, default, 'Thank you!', default); ")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        
    }
    
    
   
    
    
    
    
    
	
	

}
