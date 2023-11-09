<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Make a Quote for my Tree Cutting Service</title></head>
<body>
	<div align="center">
		<p> ${errorOne } </p>
		<p> ${errorTwo } </p>
		<form action="register">
		<h1>Make a Bill</h1>
			<table border="1" cellpadding="5">
				<tr>
					<th>Price: </th>
					<td align="left" colspan="3">
						<input type="number" step=0.01 name="price" size="45"  value="00.00" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Date Completed: </th>
					<td align="left" colspan="3">
						<input type="text"  name="complete" size="45"  value="YYYY-MM-DD" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Notes: </th>
					<td align="center" colspan="3">
						<input type="text" name="notes" size="45" value="Notes " onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Status: </th>
					<td align="center" colspan="3">
						<input type="radio"  id="p" name="pending" value="pending">
						<label for="p">Pending</label><br>
						<input type="radio" id="pa" name="paid" value="paid">
						<label for="pa">Paid</label>
					</td>
				</tr>

					<td align="center" colspan="5">
						<input type="submit" value="Submit Quote"/>
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