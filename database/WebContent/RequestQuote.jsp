<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Request Quote Page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Width</th>
                <th>Height</th>
                <th>Address</th>
                <th>Proximity to House</th>
                <th>Number of Trees</th>
                <th>Picture 1</th>
                <th>Picture 2</th>
                <th>Picture 3</th>
                <th>Date</th>
                <th>Notes</th>
            </tr>
            <c:forEach var="tree" items="${listUserTree}">
                <tr style="text-align:center">
                    <td><c:out value="${tree.width}" /></td>
                    <td><c:out value="${tree.height}" /></td>
                    <td><c:out value="${tree.address} ${users.city} ${users.state} ${users.zipcode}" /></td>
                    <td><c:out value="${tree.distance}" /></td>
                    <td><c:out value="${tree.tree_amt}" /></td>
                    <td><c:out value="${tree.image_1}" /></td>
                    <td><c:out value="${tree.image_2}" /></td>
                    <td><c:out value="${tree.image_3}" /></td>
                    <td><c:out value="${tree.date}" /></td>
                    <td><c:out value="${tree.notes}" /></td>
                </tr>
            </c:forEach>
        </table>
        <a href="https:google.com">
  		<button>Submit all Entries</button>
		</a><br><br><br><br><br>
	</div>
	</div>

</body>
</html>