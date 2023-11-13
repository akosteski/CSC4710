<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Quote Negotiations</title></head>
<body>
<form action = "viewMessages" method = "post">
  		<input type="submit" value="Back to  Quotes "/>
  		</form>
	<div align="center">
		 <form action="sendResponse" method="post">
		<h1>Write a Response</h1>
			<table border="1" cellpadding="5">
				<tr>
					<th>Message: </th>
					<td align="center" colspan="30">
						<textarea  name="msg" rows = "4" cols = "45" onfocus="this.value=''">Type Your Message Here...</textarea>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Send Response"/>
					</td>
				</tr>
				
			</table>
			<br>
			<br>
			<br>
		</form>
	</div>
</body>