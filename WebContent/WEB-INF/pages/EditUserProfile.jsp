<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/formSetup.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>
</title>
</head>
<body>
	<div align="right">
		<spring:url value="/home.do" var="homeUrl">
		</spring:url>
		<a href="${homeUrl}">Home</a>
	    <sec:authorize ifAnyGranted="ROLE_USER">			
			<spring:url value="/changePassword.do" var="changePasswordUrl">
		    </spring:url>
		    <a href="${changePasswordUrl}">Change Password</a>	
	    </sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_USER">		    
			<spring:url value="/logout.do" var="logoutUrl">
		    </spring:url>		
			<a  href="${logoutUrl}">Logout</a>
		</sec:authorize>		
	</div>
	<hr/>
		<form:form method="POST" commandName="user">
			<form:errors path="*" cssClass="errorblock" element="div" />
			<form:input type="hidden" path="action" />
			<div align="center">
			<table>
				<tr>
					<td align="center" colspan="2"><font size="6" color="brown">Please
							edit the following details</font><hr/><br/></td>
				</tr>
				<tr>
					<td class="tdblue" align="left" width="50%"><b>UserId</b></td>
					<td align="left" width="50%">${user.userid} <form:input
							type="hidden" path="userid" />
					</td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>First Name</b></td>
					<td align="left"><form:input path="firstname" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>Last Name</b></td>
					<td align="left"><form:input path="lastname" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>Email</b></td>
					<td align="left"><form:input path="email" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>Country</b></td>
					<td align="left"><form:input path="country" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>Zip</b></td>
					<td align="left"><form:input path="zip" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>State</b></td>
					<td align="left"><form:input path="state" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>City</b></td>
					<td align="left"><form:input path="city" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>Address</b></td>
					<td align="left"><form:input path="address" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>Phone</b></td>
					<td align="left"><form:input path="phone" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="left"><b>Fax</b></td>
					<td align="left"><form:input path="fax" size="30"
							maxlength="120" /></td>
				</tr>
				<tr>
					<td class="tdblue" align="center" colspan="2"><br/><hr/><input type="submit"
						value="submit"></td>
				</tr>
			</table>
			</div>
		</form:form> 
		<br>
</body>
</html>