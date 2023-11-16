<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
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
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Birthday</th>
                <th>Phone Number</th>
                <th>Credit Card</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.birthday}" /></td>
                    <td><c:out value="${users.phone}" /></td>
                    <td><c:out value="${users.credit}" /></td>
                </tr>
            </c:forEach>
        </table>
        
         <table border="1" cellpadding="6">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
                <th>ID</th>
                <th># of Trees</th>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>User Email</th>
            </tr>
            <c:forEach var="quotes" items="${listQuote}">
                <tr style="text-align:center">
                    <td><c:out value="${quotes.quoteID}" /></td>
                    <td><c:out value="${quotes.tree_amt}"/></td>
                    <td><c:out value="${quotes.price}" /></td>
                    <td><c:out value="${quotes.start_time}" /></td>
                    <td><c:out value="${quotes.end_time}" /></td>
                    <td><c:out value="${quotes.status}" /></td>
                    <td><c:out value="${quotes.email}" /></td>

                </tr>
            </c:forEach>
        </table>
        
        <table border="1" cellpadding="6">
            <caption><h2>List of Trees</h2></caption>
            <tr>
                <th>Tree ID</th>
                <th>Quote ID</th>
                <th>Width</th>
                <th>Height</th>
                <th>Address</th>
                <th>Distance</th>
                <th>Images</th>
                <th>Notes</th>
                <th>Date</th>
                
            </tr>
            <c:forEach var="trees" items="${listTree}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.treeID}" /></td>
                    <td><c:out value="${trees.quoteID}" /></td>
                    <td><c:out value="${trees.width}" /></td>
                    <td><c:out value="${trees.height}" /></td>
                    <td><c:out value="${trees.address} ${trees.city} ${trees.state} ${trees.zipcode}" /></td>
                    <td><c:out value="${trees.distance}" /></td>
                    <td><a href="${trees.image1}" target="_blank"> image 1</a> <a href="${trees.image2}" target="_blank"> image 2</a> <a href="${trees.image3}" target="_blank"> image 3</a> </td>
                    <td><c:out value="${trees.notes}" /></td>
                    <td><c:out value="${trees.date}" /></td>

                </tr>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>