<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/formSetup.js"></script>
<script>
	$(document).ready(function(){
		getSecretPasscode();
	});
	function getSecretPasscode(){
		$.ajax({
			url : 'createSecretCode.do',
			async : true,
			cahce : false,
			dataType : 'JSON',
			success : function(data) {
				$('#secretCodeKey').val(data.imagefileAttrib);
				$('#secretCodeImage').hide();
				$('#secretCodeImage').attr('src',data.imagefileAttrib).fadeIn('slow');
            }
        });
	}
</script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title></title>
</head> 
 <body>    
	<div align="right">
		<spring:url value="/home.do" var="homeUrl">
		</spring:url>
		<a href="${homeUrl}">Home</a>
	 	<sec:authorize ifAnyGranted="ROLE_USER">
	        <spring:url value="/editUserProfile.do" var="editUserUrl">
	          <spring:param name="userid" value="${userid}"/>
	        </spring:url>
	        <a href="${editUserUrl}">Edit My Profile</a>
		</sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_USER">		    
			<spring:url value="/logout.do" var="logoutUrl">
		    </spring:url>		
			<a  href="${logoutUrl}">Logout</a>
		</sec:authorize>		
	</div>
	<hr/>
	<div align="center">
		<form:form method="POST" commandName="cpass" action="changePassword.do">
		<form:errors path="*" cssClass="errorblock" element="div" />
		 <font size="8" color="brown">Change Password</font><br/><br/>
		 <table>	 
			 <tr bgcolor="#FFFFFF">
			      <td align="right"><b>User Id: </b></td>
			      <td align="left">
			         <form:input path="userid" size="30" maxlength="120"/>
			      </td>
			 </tr>		 
			 <tr bgcolor="#FFFFFF">
			      <td align="right"><b>New Password: </b></td>
			      <td align="left">
			         <form:input path="password" size="30" maxlength="120"/>
			      </td>
			 </tr>	
			 <tr bgcolor="#FFFFFF">
			      <td align="right"><b>Confirm New Password: </b></td>
			      <td align="left">
			         <form:input path="confirmPassword" size="30" maxlength="120"/>
			      </td>
			 </tr>				 
			 <tr bgcolor="#FFFFFF">
			      <td align="right"><b>Old Password: </b></td>
			      <td align="left">
			         <form:input path="oldPassword" size="30" maxlength="120"/>
			      </td>
			 </tr>
			 <tr bgcolor="#FFFFFF">
			      <td align="right"><b> </b></td>
			      <td align="center">
			         <img id="secretCodeImage" src=""></img>
			      </td>
			 </tr>
			 <tr bgcolor="#FFFFFF">
			      <td align="right"><b>Secret Code:</b><br/><em>(Please type the code above)</em></td>
			      <td align="left">
			         <form:input path="secretCode" size="30" maxlength="120"/>
			         <form:input type="hidden" path="secretCodeKey"/>
			      </td>
			 </tr>	
		</table>
		<br/><br/>
		<div align="center"><input type="submit" value="Change Password"></div>
		</form:form>
		<br>    
	</div>
</body>
</html>