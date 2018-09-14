<%@page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/formSetup.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Show Error Page</title>
</head>
<body>
	<h1>
		<font color="brown" size="9">Oops...</font>
	</h1>
	<table width="100%" border="1" style="color: #000080; size: 5;">
		<tr valign="top">
			<td width="40%"><b>Error:</b></td>
			<td>${pageContext.exception}</td>
		</tr>
		<tr valign="top">
			<td><b>URI:</b></td>
			<td><c:out value="${pageContext.errorData.requestURI}" /></td>
		</tr>
		<tr valign="top">
			<td><b>Servlet Name</b></td>
			<td><c:out value="${pageContext.errorData.servletName}" /></td>
		</tr>
		<tr valign="top">
			<td><b>Type Of Exception</b></td>
			<td>
				<c:out value="${pageContext.exception.class.name}" />
			</td>
		</tr>
		<tr valign="top">
			<td><b>Status code:</b></td>
			<td><c:out value="${pageContext.errorData.statusCode}" /></td>
		</tr>
		<tr valign="top">
			<td><b>Stack trace:</b></td>
			<td><c:forEach var="trace"
					items="${pageContext.exception.stackTrace}">
					<p>
						<c:out value="${trace}" />
					</p>
				</c:forEach>
			</td>
		</tr>
	</table>
</body>
</html>