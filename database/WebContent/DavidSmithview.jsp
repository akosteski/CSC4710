<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to David Smith view</title>
</head>

<center><h1>Welcome! You have been successfully logged in to David Smith</h1> </center>
<body>
	 <center>
	 <div>
  		<form action = "viewQuotes" method = "post">
  		<input type="submit" value="View All Quotes"/>
  		</form>
	</div>
	<br>
	<br>
	<div>
  		<button>Bills</button>
	</div>
	 	
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p> You can find all the quotes a client has requested.</p>
	 </center>

</body>
</html>



