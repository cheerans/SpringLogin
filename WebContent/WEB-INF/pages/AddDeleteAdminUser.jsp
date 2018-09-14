<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/formSetup.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("table").addClass("tableNoRowcolBorder");
		$("th").addClass("thwhite");
		$("tr:even").addClass("alternaterow");
        $(".delete").click(function(e){
			var userid = $(this).attr('href');
			 $('form').get(0).setAttribute('action', 'deleteAdmin.do?deleteUserid=' + userid);			
			$('#addEditAdmin').submit();
			e.preventDefault();
        }); 
	});
</script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Find Table Tennis Racket / Ping Pong racket</title>
</head>
<body>
	<div align="right">
		<spring:url value="/home.do" var="homeUrl">
		</spring:url>
		<a href="${homeUrl}">Home</a>
		<spring:url value="/newUserProfile.do" var="newUserUrl">
		</spring:url>
		<a href="${newUserUrl}">Register New User</a>
		<sec:authorize ifAnyGranted="ROLE_USER">
			<spring:url value="/logout.do" var="logoutUrl">
			</spring:url>
			<a href="${logoutUrl}">Logout</a>
		</sec:authorize>
	</div>
	<hr />
	<form:form id="addEditAdmin" method="POST" commandName="adminForm" action="addNewAdmin.do">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div align="center">
			<font size="6" color="brown">Manage user rights</font><br />
			<br /> Please select a user to be made admin:
			<form:select path="newAdminUserid">
				<c:forEach var="thisUser" items="${adminForm.userList}">
					<form:option value="${thisUser}">${thisUser}</form:option>
				</c:forEach>
			</form:select>
			<form:input type="submit" path="" value="Submit" />
			<br /> <br />
		</div>
		<div align="center">
			<b>[ User details ]<br />***********<br />
			</b>
			<table style="border: 1px solid black;">
				<tr>
					<th>User id</th>
					<th>Access type</th>
					<th>Admin rights</th>
				</tr>
				<c:set var="count" value="${1}" />
				<c:forEach var="adminUser" items="${adminForm.adminList}">
					<tr>
						<td>
							<c:out value="${adminUser}"/>
						</td>
						<td>administrator</td>
						<td align="center">
							<a class="delete" href="${adminUser}">delete</a>
						</td>
					</tr>
				</c:forEach>
				<c:forEach var="superUser" items="${adminForm.superuserList}">
					<tr class="specialrow">
						<td>
							<c:out value="${superUser}"/>
						</td>
						<td>super user</td>
						<td align="center"></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
	</form:form>
</body>
</html>