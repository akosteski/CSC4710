<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Request a Quote for my Tree Cutting Service</title></head>
<body>
	<div align="center">
		 <form action="makeTree" method="post">
		<h1>Submit a Tree for my Tree Cutting Service</h1>
			<table border="1" cellpadding="5">
				<tr>
					<th>Width: </th>
					<td align="left" colspan="3">
						<input type="number" step=0.01 name="width" size="45"  value="00.00" onfocus="this.value=''">
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
						<input type="text" name="address" size="45" value="42 W Warren Ave " onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>City: </th>
					<td align="center" colspan="3">
						<input type="text" name="city" size="45" value="Detroit " onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>State: </th>
					<td align="center" colspan="3">
						<input type="text" name="state" size="45" value="MI" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Zip Code: </th>
					<td align="center" colspan="3">
						<input type="text" name="zipcode" size="45" value="48315 " onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Proximity to House: </th>
					<td align="left" colspan="3">
						<input type="number" step=0.01 name="distance" size="45" value="00.00" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Picture 1: </th>
					<td align="center" colspan="3">
						<input type="text" name="image_1" size="45" value="(URL)" onfocus="this.value=''"> 
					</td>	
				</tr>
				<tr>
					<th>Picture 2: </th>
					<td align="center" colspan="3">
						<input type="text" name="image_2" size="45" value="(URL)" onfocus="this.value=''">  
					</td>	
				</tr>
				<tr>
					<th>Picture 3: </th>
					<td align="center" colspan="3">
						<input type="text" name="image_3" size="45" value="(URL)" onfocus="this.value=''"> 
					</td>	
				</tr>
					<th>Date: </th>
					<td align="center" colspan="3">
						<input type="text" name="date" size="45" value="YYYY-MM-DD" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Notes: </th>
					<td align="center" colspan="30">
						<input type="text" name="notes" size="45" value="notes" onfocus="this.value=''">
					</td>
				</tr>
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