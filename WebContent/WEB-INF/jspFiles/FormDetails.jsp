<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<body bgcolor="pink">

<form:form method="POST" commandName="user">
	<table>
		<tr>
			<td>Enter Ur Name :</td>
			<td><form:input path="name" /></td>
		</tr>
		
		
		<tr>
			<td>Enter Degree</td>
			<td><form:input path="degree" /></td>
		</tr>
		
		<tr>
			<td>Enter Mark</td>
			<td><form:input path="mark" /></td>
		</tr>
		
		<tr>
			<td>Enter Address</td>
			<td><form:input path="address" /></td>
		</tr>
		
		<tr>
			<td>Enter Mail ID</td>
			<td><form:input path="mailId" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="submit"></td>
		</tr>
	</table>
</form:form>

</body>
</html>