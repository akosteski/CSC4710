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
@WebServlet("/negotiateQuoteDAO")
public class negotiateQuoteDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public negotiateQuoteDAO(Connection dbInstance) { connect = dbInstance; }
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    
    public List<negotiateQuote> listAllNQ() throws SQLException {
        List<negotiateQuote> listNegQ = new ArrayList<negotiateQuote>();        
        String sql = "SELECT * FROM NegotiateQuote";   
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int negotiateQID = resultSet.getInt("negotiateQID");
            int quoteID = resultSet.getInt("quoteID");
            String email = resultSet.getString("email");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time"); 
            String msg = resultSet.getString("msg"); 
            String date = resultSet.getString("date"); 

            negotiateQuote negQs = new negotiateQuote(negotiateQID, quoteID, email, price, start_time, end_time, msg, date);
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
            int negotiateQID = resultSet.getInt("negotiateQID");
            String email = resultSet.getString("email");
            double price = resultSet.getDouble("price");
            String start_time = resultSet.getString("start_time");
            String end_time = resultSet.getString("end_time"); 
            String msg = resultSet.getString("msg"); 
            String date = resultSet.getString("date"); 

            negotiateQuote quoteConvo = new negotiateQuote(negotiateQID, quoteID, email, price, start_time, end_time, msg, date);
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
    
    public boolean delete(int negQs) throws SQLException {
        String sql = "DELETE FROM NegotiateQuote WHERE negotiateQID = ?";        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, negQs);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(negotiateQuote negQs) throws SQLException {
        String sql = "update NegotiateQuote set price=?, start_time=?, end_time= ?, msg=?, date=? where negotiateQID= ?";
        
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
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            String credit = resultSet.getString("credit");
            String phone = resultSet.getString("phone");
            user = new user(email, firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, credit, phone);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    } 
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = { "drop table if exists NegotiateQuote; ",
		        ("CREATE TABLE if not exists NegotiateQuote( " +
		        	"negotiateQID INTEGER NOT NULL AUTO_INCREMENT, " +
		            "quoteID INTEGER NOT NULL, " +
		        	"email VARCHAR(50) NOT NULL, " +
		        	"price DECIMAL(7,2) DEFAULT 0, " +
		            "start_time DATE DEFAULT '0001-01-01', " +
		            "end_time DATE DEFAULT '0001-01-01', " +
		            "msg VARCHAR(500)," +
		            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
		            "PRIMARY KEY (negotiateQID), " +
		            "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID)," +
		            "FOREIGN KEY (email) REFERENCES User(email));" )
		        
				};
        String[] TUPLES = {("insert into NegotiateQuote(quoteID, email, price, start_time, end_time, msg, date)"+
        			"values (28, 'amelia@gmail.com', default, default, default, 'Please cut my trees!', default);")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        
    }
    
    
   
    
    
    
    
    
	
	

}
