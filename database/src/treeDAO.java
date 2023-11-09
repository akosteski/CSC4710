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
public class treeDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public treeDAO(){}
	
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
    
    public List<tree> listAllTrees() throws SQLException {
        List<tree> listTree = new ArrayList<tree>();        
        String sql = "SELECT * FROM Tree";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int treeID = resultSet.getInt("treeID");
            double width = resultSet.getDouble("width");
            double height = resultSet.getDouble("height");
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String state = resultSet.getString("state"); 
            String zipcode = resultSet.getString("zipcode"); 
            double distance = resultSet.getDouble("distance");
            String image1 = resultSet.getString("image1");
            String image2 = resultSet.getString("image2");
            String image3 = resultSet.getString("image3");
            String notes = resultSet.getString("notes"); 
            String date = resultSet.getString("date");
            int quoteID = resultSet.getInt("quoteID");

             
            tree trees = new tree(treeID, width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date, quoteID);
            listTree.add(trees);
            
        }        
        resultSet.close();
        disconnect();        
        return listTree;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(tree trees) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into Tree(width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setDouble(1, trees.getWidth());
			preparedStatement.setDouble(2, trees.getHeight());
			preparedStatement.setString(3, trees.getAddress());
			preparedStatement.setString(4, trees.getCity());
			preparedStatement.setString(5, trees.getState());		
			preparedStatement.setString(6, trees.getZipcode());		
			preparedStatement.setDouble(7, trees.getDistance());		
			preparedStatement.setString(8, trees.getImage1());		
			preparedStatement.setString(9, trees.getImage2());		
			preparedStatement.setString(10, trees.getImage3());
			preparedStatement.setString(11, trees.getNotes());
			preparedStatement.setString(12, trees.getDate());
			preparedStatement.setInt(13, trees.getQuoteID());



		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(int treeID) throws SQLException {
        String sql = "DELETE FROM Tree WHERE treeID = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, treeID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(tree trees) throws SQLException {
        String sql = "update Tree set width=?, height=?, address= ?, city=?, state=?, zipcode=?, distance=?, image1=?, image2=?, image3=?, notes=?, date=? where treeID=?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, trees.getTreeID());
        preparedStatement.setDouble(2, trees.getWidth());
		preparedStatement.setDouble(3, trees.getHeight());
		preparedStatement.setString(4, trees.getAddress());
		preparedStatement.setString(5, trees.getCity());
		preparedStatement.setString(6, trees.getState());		
		preparedStatement.setString(7, trees.getZipcode());		
		preparedStatement.setDouble(8, trees.getDistance());		
		preparedStatement.setString(9, trees.getImage1());		
		preparedStatement.setString(10, trees.getImage2());		
		preparedStatement.setString(11, trees.getImage3());
		preparedStatement.setString(12, trees.getNotes());
		preparedStatement.setString(13, trees.getDate());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public tree getTree(int treeID) throws SQLException {
    	tree tree = null;
        String sql = "SELECT * FROM Tree WHERE treeID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, treeID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            double width = resultSet.getDouble("width");
            double height = resultSet.getDouble("height");
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String state = resultSet.getString("state"); 
            String zipcode = resultSet.getString("zipcode"); 
            double distance = resultSet.getDouble("distance");
            String image1 = resultSet.getString("image1");
            String image2 = resultSet.getString("image2");
            String image3 = resultSet.getString("image3");
            String notes = resultSet.getString("notes"); 
            String date = resultSet.getString("date");
            int quoteID = resultSet.getInt("quoteID");
            
            tree = new tree(treeID, width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date, quoteID);
        }
         
        resultSet.close();
        statement.close();
         
        return tree;
    }    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = { "drop table if exists Tree; ",
					        ("CREATE TABLE if not exists Tree( " +
					            "treeID INTEGER NOT NULL AUTO_INCREMENT, " +
					        	"quoteID INTEGER NOT NULL, " +
					            "width DECIMAL (3,2) DEFAULT 1, " +
					            "height DECIMAL (4,2) DEFAULT 1, " +
					            "address VARCHAR(75) NOT NULL, " +
					            "city VARCHAR(20) NOT NULL, " +
					            "state VARCHAR(2) NOT NULL, "+ 
					            "zipcode VARCHAR(5) NOT NULL, "+ 
					            "distance DECIMAL(5,2) DEFAULT 1," + 
					            "image1 VARCHAR(2048),"+ //maximum URL character length in 2048
					            "image2 VARCHAR(2048),"+ 
					            "image3 VARCHAR(2048)," +
					            "notes VARCHAR(500)," +
					            "date DATE NOT NULL," +
					            "PRIMARY KEY (treeID) "+
					            "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID));")
					        
        					};
        String[] TUPLES = {("insert into Tree")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
