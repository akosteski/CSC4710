<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Quotes</title>
</head>
<body>
<a href="DavidSmithview.jsp"target ="_self" > Back to Home</a><br><br> 

<h1 align = "center">List All Quotes</h1>
    <div align="center">
    <form action="selectQuote" method="post">
	<label>Select a quote to view its trees</label>
    	<select name = "quoteID">
 			<c:forEach items = "${listQuote}" var="quotes" varStatus="loop">
 				<option value="${quotes.quoteID}"><c:out value="${quotes.quoteID}" /></option>
 			</c:forEach>
 		</select>
 		
	<input type = "submit" value = "Select Quote"/>
	</form>
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
</body>
</html>