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
	
	public treeDAO(Connection dbInstance) { connect = dbInstance; }
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    
    public List<tree> listAllTrees() throws SQLException {
        List<tree> listTree = new ArrayList<tree>();        
        String sql = "SELECT * FROM Tree";      
              
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
        return listTree;
    }
    
    public List<tree> listQuoteTrees(int quoteID) throws SQLException {
        List<tree> listQuoteTree = new ArrayList<tree>();        
        String sql = "SELECT * FROM Tree WHERE quoteID=?";      
              
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
        ResultSet resultSet = preparedStatement.executeQuery();
         
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
             
            tree trees = new tree(treeID, width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date, quoteID);
            listQuoteTree.add(trees);
            
        }        
        resultSet.close();
                
        return listQuoteTree;
    }
    
    public void insert(tree trees) throws SQLException {
    	         
		String sql = "insert into Tree(width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date, quoteID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    
    public boolean delete(int quoteID) throws SQLException {
        String sql = "DELETE FROM Tree WHERE quoteID = ?";        
        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(tree trees) throws SQLException {
        String sql = "update Tree set width=?, height=?, address= ?, city=?, state=?, zipcode=?, distance=?, image1=?, image2=?, image3=?, notes=?, date=? where treeID=?";
        
        
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
    
   /* public tree countTree(int quoteID) throws SQLException {
    	tree tree = null;
    	String sql = "SELECT COUNT(*) INTO @count FROM Tree WHERE quoteID = ?; " + "Update Quote set tree_amt = @count where quoteID=?; ";
    	
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
        
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            //int count = @count;            
        }
         
        resultSet.close();
        statement.close();
         
        return tree;
    } */
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	
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
					            "distance DECIMAL(6,2) DEFAULT 1," + 
					            "image1 VARCHAR(2048), "+ //maximum URL character length is 2048
					            "image2 VARCHAR(2048), "+ 
					            "image3 VARCHAR(2048), " +
					            "notes VARCHAR(500), " +
					            "date DATE NOT NULL," +
					            "PRIMARY KEY (treeID), " +
					            "FOREIGN KEY (quoteID) REFERENCES Quote(quoteID));")
					        
        					};
        String[] TUPLES = {("insert into Tree (treeID, quoteID, width, height, address, city, state, zipcode, distance, image1, image2, image3, notes, date) values" +
        					"(155, 414, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'Blah Blah Blue', '1999-04-12'), " +
        					"(48, 414, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'Red Team Leader', '1999-04-12'), " +
        					"(99, 414, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'Going Going gone!', '1999-04-12'), " + 
        					"(6, 28, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'sdfghjk', '1999-04-12'), " +
        					"(31, 28, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'We got this!', '1999-04-12'), " +
        					"(56, 3, 3.22, 55.55, '50334 Big Lane', 'Detroit', 'MI', '48752', 123.55, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy2kn_BiOQWJVw0-8szYxQLgAiEtyNP7ZYfA&usqp=CAU', 'Last PUsh!', '1999-04-12');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        
    }
    
    
   
    
    
    
    
    
	
	

}
