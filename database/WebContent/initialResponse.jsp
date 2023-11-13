<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Respond to a Quote Request</title></head>
<body>
<form action = "viewQuotes" method = "post">
  		<input type="submit" value="Back to All Quotes "/>
  		</form>
	<div align="center">
		 <form action="sendFirstResponse" method="post">
		<h1>Respond to this Quote</h1>
			<table border="1" cellpadding="5">
				<tr>
					<th>Price: </th>
					<td align="left" colspan="3">
						<input type="number" step=0.01 name="price" size="45"  value="00000.00" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Start Time: </th>
					<td align="left" colspan="3">
						<input type="text" name="start_time" size="45" value="YYYY-MM-DD" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>End Time: </th>
					<td align="left" colspan="3">
						<input type="text" name="end_time" size="45" value="YYYY-MM-DD" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Accept this Request?: </th>
					<td align="left" colspan="3">
						<input type="radio" id = "accept" name="status" value="Accepted"/>
						<label for="accept">Yes</label>
						<input type="radio" id = "reject" name="status" value="Rejected"/>
						<label for="reject">No</label>
					</td>
				</tr>
						
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