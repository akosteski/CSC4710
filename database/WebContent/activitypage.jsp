<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center>

 
	<body>
	 <center>
		 <button onclick="window.location.href='Tree.jsp';"> Request Quote
		 </button></br></br>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p> You can find quotes you've made here.</p>
		 </center>
	</body>
</html>