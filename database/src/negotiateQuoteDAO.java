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
    
    public List<negQ> listAllNQ() throws SQLException {
        List<negQ> listUser = new ArrayList<negQ>();        
        String sql = "SELECT * FROM NegotiateQuote";   
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
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

             
            negQ negQs = new negQ(email,firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, credit, phone);
            listUser.add(negQs);
            
        }        
        resultSet.close();
        
                
        return listUser;
    }
    
    public void insert(user users) throws SQLException {
		String sql = "insert into User(email, firstName, lastName, password, birthday,adress_street_num, adress_street,adress_city,adress_state,adress_zip_code,credit,phone) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getBirthday());
			preparedStatement.setString(6, users.getAdress_street_num());		
			preparedStatement.setString(7, users.getAdress_street());		
			preparedStatement.setString(8, users.getAdress_city());		
			preparedStatement.setString(9, users.getAdress_state());		
			preparedStatement.setString(10, users.getAdress_zip_code());		
			preparedStatement.setString(11, users.getCredit());
			preparedStatement.setString(12, users.getPhone());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?,password = ?,birthday=?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, credit=?, phone=?, where email = ?";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAdress_street_num());		
		preparedStatement.setString(7, users.getAdress_street());		
		preparedStatement.setString(8, users.getAdress_city());		
		preparedStatement.setString(9, users.getAdress_state());		
		preparedStatement.setString(10, users.getAdress_zip_code());		
		preparedStatement.setString(11, users.getCredit());
		preparedStatement.setString(12, users.getPhone());
         
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
		        	"price DECIMAL(5,2) DEFAULT 0, " +
		            "start_time DATE DEFAULT '1999-12-31', " +
		            "end_time DATE DEFAULT '1999-12-31', " +
		            "msg VARCHAR(500)," +
		            "date DATE DEFAULT GETDATE(), " +
		            "PRIMARY KEY (negotiateQID), " +
		            "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID)," +
		            "FOREIGN KEY (email) REFERENCES User(email));" )
		        
				};
        String[] TUPLES = {("insert into NegotiateQuote(negotiateID, quoteID, email, price, start_time, end_time, msg, date)"+
        			"values ();")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        
    }
    
    
   
    
    
    
    
    
	
	

}
