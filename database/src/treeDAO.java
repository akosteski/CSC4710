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
@WebServlet("/treeDAO")
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
    
    public List<tree> listQuoteTrees() throws SQLException {
        List<tree> listQuoteTree = new ArrayList<tree>();        
        String sql = "SELECT * FROM Tree WHERE quoteID=?";      
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
            listQuoteTree.add(trees);
            
        }        
        resultSet.close();
        disconnect();        
        return listQuoteTree;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(tree trees) throws SQLException {
    	connect_func();         
		String sql = "insert into Tree(width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date, quoteID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    
    public tree countTree(int treeID, int quoteID) throws SQLException {
    	tree tree = null;
    	String sql = "SELECT COUNT(*) INTO @count FROM Tree WHERE quoteID = ?; " + "Update Quote set tree_amt = @count where quoteID=?; ";
    	
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
					            "image1 VARCHAR(2048),"+ //maximum URL character length is 2048
					            "image2 VARCHAR(2048),"+ 
					            "image3 VARCHAR(2048)," +
					            "notes VARCHAR(500)," +
					            "date DATE NOT NULL," +
					            "PRIMARY KEY (treeID), " +
					            "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID));")
					        
        					};
        String[] TUPLES = {("insert into Tree (treeID, quoteID, width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date) values" +
        					"(155, 414, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'image1', 'image2', 'image3', 'Blah Blah Blue', '1999-04-12'), " +
        					"(48, 414, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'image1', 'image2', 'image3', 'Red Team Leader', '1999-04-12'), " +
        					"(99, 414, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'image1', 'image2', 'image3', 'Going Going gone!', '1999-04-12'), " + 
        					"(6, 28, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'image1', 'image2', 'image3', 'sdfghjk', '1999-04-12'), " +
        					"(31, 28, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'image1', 'image2', 'image3', 'We got this!', '1999-04-12'), " +
        					"(56, 3, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'image1', 'image2', 'image3', 'Last PUsh!', '1999-04-12');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
