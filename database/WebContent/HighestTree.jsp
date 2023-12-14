<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Highest Tree</title>
</head>
<body>

<div align = "center">
	

<h1>Highest Tree</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Highest Tree</h2></caption>
            <tr>
                <th>Tree ID</th>
                <th>Quote ID</th>
                <th>Width</th>
                <th>Height</th>
                <th>Address</th>
                <th>Distance</th>
            </tr>
            

              
            <c:forEach var="tree" items="${HighestTree}">
                <tr style="text-align:center">
                    <td><c:out value="${tree.treeID}" /></td>
                    <td><c:out value="${users.quoteID}" /></td>
                    <td><c:out value="${users.width}" /></td>
                    <td><c:out value="${users.height}" /></td>
                    <td><c:out value="${users.adress}" /></td>
                    <td><c:out value="${users.distance}" /></td>
                </tr>
            </c:forEach>
        </table>
            
           
            
        </table>
        </div>
        </div>