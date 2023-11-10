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
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>Request a Quote Form</h1>
    <div align="center">
    	<form action = "createQuote">
    	
    	<input type = "submit" value = "Submit Request"/>
    	<button onclick="window.location.href='Tree.jsp';">Add Tree</button>
        <table border="1" cellpadding="6">
            <caption><h2></h2></caption>
            <tr>
                <th>Width</th>
                <th>Height</th>
                <th>Address</th>
                <th>Proximity to House</th>
                <th>Picture 1</th>
                <th>Picture 2</th>
                <th>Picture 3</th>
                <th>Date</th>
                <th>Notes</th>
            </tr>
            <c:forEach var="tree" items="${listQuoteTree}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.width}" /></td>
                    <td><c:out value="${trees.height}" /></td>
                    <td><c:out value="${trees.address} ${trees.city} ${trees.state} ${trees.zipcode}" /></td>
                    <td><c:out value="${trees.distance}" /></td>
                    <td><c:out value="${trees.image1}" /></td>
                    <td><c:out value="${trees.image2}" /></td>
                    <td><c:out value="${trees.image3}" /></td>
                    <td><c:out value="${trees.date}" /></td>
                    <td><c:out value="${trees.notes}" /></td>
                </tr>
            </c:forEach>
        </table>
	</form>
	</div>
	</div>

</body>
</html>