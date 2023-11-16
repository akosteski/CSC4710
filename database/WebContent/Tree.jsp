<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Create a Tree</title></head>
<body>
	<div align="center">
		 <form action="createTree" method="post">
		<h1>Add a tree to your Quote Request</h1>
			<table border="1" cellpadding="5">
				<tr>
					<th>Width: </th>
					<td align="left" colspan="3">
						<input type="number" step=0.01 name="width" size="45"  value="0.00" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Height: </th>
					<td align="left" colspan="3">
						<input type="number" step=0.01 name="height" size="45"  value="00.00" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Street Address: </th>
					<td align="center" colspan="3">
						<input type="text" name="address" size="45" value="42 W Warren Ave" required onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>City: </th>
					<td align="center" colspan="3">
						<input type="text" name="city" size="45" value="Detroit" required onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>State: </th>
					<td align="center" colspan="3">
						<input type="text" name="state" size="45" value="MI" required onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Zip Code: </th>
					<td align="center" colspan="3">
						<input type="text" name="zipcode" size="45" value="48315" required onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Proximity to House: </th>
					<td align="left" colspan="3">
						<input type="number" step=0.01 name="distance" size="45" value="0000.00" required onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Picture 1: </th>
					<td align="center" colspan="3">
						<input type="text" name="image1" size="45" value="(URL)" onfocus="this.value=''"> 
					</td>	
				</tr>
				<tr>
					<th>Picture 2: </th>
					<td align="center" colspan="3">
						<input type="text" name="image2" size="45" value="(URL)" onfocus="this.value=''">  
					</td>	
				</tr>
				<tr>
					<th>Picture 3: </th>
					<td align="center" colspan="3">
						<input type="text" name="image3" size="45" value="(URL)" onfocus="this.value=''"> 
					</td>	
				</tr>
				<tr>
					<th>Date: </th>
					<td align="center" colspan="3">
						<input type="text" name="date" size="45" value="YYYY-MM-DD" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Notes: </th>
					<td align="center" colspan="30">
						<textarea  name="notes" rows = "4" cols = "45" onfocus="this.value=''">Type Your Note Here...</textarea>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Submit Tree"/>
					</td>
				</tr>
				
			</table>
			<br>
			<br>
			<br>
			<a href="login.jsp" target="_self">Return to Login Page</a>
		</form>
	</div>
</body>