<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Message Board</title></head>
<body>
<form action = "msgGoBack" method = "post">
  		<input type="submit" value="Back to Quotes "/>
  		</form>
<div align="center">
<h1>Message Board</h1>
		 <form action="response.jsp" method="post">
		 <input type="submit" value="Write a Message"/>
		</form>
		
	<table border="1" cellpadding="6">
	<caption><h3>Selected Request: ${QuoteID}</h3></caption>
            <tr>
                <th>Sender</th>
                <th>Message</th>
                <th>Date</th>
            </tr>
            <c:forEach var="convo" items="${listMessage}">
                <tr style="text-align:center">
                    <td><c:out value="${convo.email}" /></td>
                    <td><c:out value="${convo.msg}" /></td>
                    <td><c:out value="${convo.date}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
</body>