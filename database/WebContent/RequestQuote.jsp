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
	<a href="activitypage.jsp"target ="_self" >Return to Home</a><br><br> 

<h1>Request a Quote Form</h1>
   <div align="center">
     <div align="center">
   	<form action="generateQuote" method="post">
    	<input type = "submit" value = "Generate a Quote"/>
	</form>
	<br>
	</div>
	
	<div align = "center">
	<form action="selectRequest" method="post">
	<label>Select the quote you would like to add a tree to.</label>
    	<select name = "quoteID">
 			<c:forEach items = "${listUserQuote}" var="quotes" varStatus="loop">
 				<option value="${quotes.quoteID}"><c:out value="${quotes.quoteID}" /></option>
 			</c:forEach>
 		</select>
	<input type = "submit" value = "Select Quote"/>
	</form>
	<br>
	<form action="makeTree" method ="post">
	<input type = "submit" value = "Add a Tree to this Quote!">
	<table border="1" cellpadding="6">
	<caption><h2>Trees in this Quote</h2></caption>
	<caption><h3>Selected Quote: ${QuoteID}</h3></caption>
            <tr>
            	<th>Tree ID</th>
                <th>Width (meters)</th>
                <th>Height (meters)</th>
                <th>Address</th>
                <th>Proximity to House (meters)</th>
                <th>Pictures</th>
                <th>Date</th>
                <th>Notes</th>
            </tr>
            <c:forEach var="trees" items="${listQuoteTree}">
                <tr style="text-align:center">
                	<td><c:out value="${trees.treeID}" /></td>
                    <td><c:out value="${trees.width}" />m</td>
                    <td><c:out value="${trees.height}" />m</td>
                    <td><c:out value="${trees.address} ${trees.city} ${trees.state} ${trees.zipcode}" /></td>
                    <td><c:out value="${trees.distance}" />m</td>
                    <td><a href="${trees.image1}" target="_blank"> image 1</a> <a href="${trees.image2}" target="_blank"> image 2</a> <a href="${trees.image3}" target="_blank"> image 3</a> </td>
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