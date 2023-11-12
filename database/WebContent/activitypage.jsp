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
		 <form action="request" method="post">
			<table border="1" cellpadding="5">

				<input type="submit" value="Request a Quote"/>

		</form></br></br>
		<form action="" method="post">
			<table border="1" cellpadding="5">

				<input type="submit" value="View all quotes"/>

		</form></br></br>
		<form action="" method="post">
			<table border="1" cellpadding="5">

				<input type="submit" value="View "/>

		</form></br></br>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 <p> You can find quotes you've made here.</p>
		 </center>
	</body>
</html>