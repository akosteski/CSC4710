<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Trees in this Quote</title>
</head>
<body>
<form action = "viewQuotes" method = "post">
  		<input type="submit" value="Back to View Quotes"/>
  		</form>

<div align = "center"> 
<h1>Trees in this Request</h1>
	<div align = "center">
	<a href="initialResponse.jsp" >
	<button>Update this Request</button>
	</a>
	<br><br>
	<form action="viewMessages" >
	<button>View Messages for this Quote</button>
	</a>
	</form>
	<table border="1" cellpadding="6">
	<caption><h3>Selected Request: ${QuoteID}</h3></caption>
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
	</div>

</body>
</html>