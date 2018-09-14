<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/formSetup.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title></title>
</head> 
 <body>    
	<div align="right">
		<spring:url value="/home.do" var="homeUrl">
		</spring:url>
		<a href="${homeUrl}">Home</a>	
		<sec:authorize ifAnyGranted="ROLE_USER">
			<spring:url value="/editUserProfile.do" var="editUserProfile">
		    </spring:url>
		    <a href="${editUserProfile}">Edit My Profile</a>
	    </sec:authorize>
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
	<form:input  type="hidden" path="action" />
	<div align="center">
	 <table class="tableNoRowcolBorder">
		 <tr>
		      <td align="center" colspan="2">
		       <font size="6" color="brown">My profile</font><hr/>
		      </td>
		 </tr>	 
		  <tr>
		      <td align="left" width="50%"><b>UserId</b></td>
		      <td align="left" width="50%">
		       ${user.userid}        
		      </td>
		 </tr>		 
		  <tr>
		      <td class="tdblue" align="left"><b>First Name</b></td>
		      <td align="left">			         
		       ${user.firstname}
		      </td>
		 </tr>
		  <tr>
		      <td class="tdblue" align="left"><b>Last Name</b></td>
		      <td align="left">			         
		        ${user.lastname}
		      </td>
		 </tr>
		  <tr>
		      <td class="tdblue" align="left"><b>Email</b></td>
		      <td align="left">
		      	${user.email}
		      </td>
		 </tr>
		  <tr>
		      <td class="tdblue" align="left"><b>Country</b></td>
		      <td align="left">			        
		         ${user.country}
		      </td>
		 </tr>
		  <tr>
		      <td class="tdblue" align="left"><b>Zip</b></td>
		      <td align="left">			        
		         ${user.zip}
		      </td>
		 </tr>
		  <tr>
		      <td class="tdblue" align="left"><b>State</b></td>
		      <td align="left">
		      	${user.state}
		      </td>
		 </tr>
		  <tr>
		      <td class="tdblue" align="left"><b>City</b></td>
		      <td align="left">
		      	${user.city}			         
		      </td>
		 </tr>
		  <tr>
		      <td class="tdblue" align="left"><b>Address</b></td>
		      <td align="left">			         
		         ${user.address}
		      </td>
		 </tr>
		  <tr>
		      <td class="tdblue" align="left"><b>Phone</b></td>
		      <td align="left">
		      	 ${user.phone}					         
		      </td>
		 </tr>
		 <tr>
		      <td class="tdblue" align="left"><b>Fax</b></td>
		      <td align="left">
		      	 ${user.fax}			         
		      </td>
		 </tr>
	</table>
	</div>
	</form:form>
	<br>    
</body>
</html>