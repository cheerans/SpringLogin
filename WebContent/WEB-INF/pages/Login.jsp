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
<title></title>
</head>
<body>  
	<div align="right">
		<sec:authorize ifAnyGranted="ROLE_USER">
			<spring:url value="/logout.do" var="logoutUrl">
		    </spring:url>
			<a  href="${logoutUrl}">Logout</a>
		</sec:authorize>	 	
		<sec:authorize ifAnyGranted="ROLE_SUPERUSER">
		        <spring:url value="/addNewAdmin.do" var="addNewAdminUrl">
		        </spring:url>
		        <a href="${addNewAdminUrl}">Manage Admin user</a>
		</sec:authorize>		
	 	<sec:authorize ifAnyGranted="ROLE_USER">
		        <spring:url value="/displayUserProfile.do" var="displayUserUrl">
		        </spring:url>
		        <a href="${displayUserUrl}">My Profile</a>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_USER">
			<spring:url value="/newUserProfile.do" var="newUserUrl">
		    </spring:url>
		    <a href="${newUserUrl}">Register New User</a>	    
		 	<spring:url value="/forgotPassword.do" var="forgotPasswordUrl">
		    </spring:url>
		    <a href="${forgotPasswordUrl}">Forgot Password</a>
		</sec:authorize>
	</div>
	<hr/>
	<div align="center">
		<form:form commandName="user" action="login.do">
		<form:errors path="*" cssClass="errorblock" element="div" />
		 <c:if test="${user.loggedIn == false}">
		 	<font size="8" color="brown">Please Login</font>
		 </c:if>
		 <br/><br/>		 

			 <form:hidden path="loggedIn" />
			 <table>	 
				  <tr bgcolor="#FFFFFF">
				      <td align="right" width="50%"><b>UserId</b></td>
				      <td align="left" width="50%">
				      	<c:choose>
				      		<c:when test="${user.loggedIn == true}">
	    						<font color="gray">&nbsp;&nbsp;&nbsp;${user.userid}</font>    						 
							</c:when> 
							<c:otherwise>
								<form:input path="userid" size="30" maxlength="120"/>
							</c:otherwise>
						</c:choose> 
				      </td>
				 </tr>
				 <tr bgcolor="#FFFFFF">
				      <td align="right"><b>Password</b></td>
				      <td align="left">
				         <c:choose>
				      		<c:when test="${user.loggedIn == true}">
				      			<font color="gray">&nbsp;&nbsp;&nbsp;Logged In</font>    						
							</c:when> 
							<c:otherwise>							
								<form:input path="password" size="30" maxlength="120"/>
							</c:otherwise>
						</c:choose> 
				      </td>
				 </tr>			 
			</table>
			<br/><br/>		
		      	<c:if test="${user.loggedIn == false}">
			      	<div align="center">
		  				<form:input path="" type="submit" value="Login"/>
		  			</div>
				</c:if> 							
			</form:form>
		</div>
</body>
</html>