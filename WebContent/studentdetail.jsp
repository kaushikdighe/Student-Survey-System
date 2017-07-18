<%@page import="com.swe642.beans.StudentBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Details</title>
</head>
<body>
<%
	StudentBean studentBean = (StudentBean)session.getAttribute("studentDetailsBean");
	String studentID = (String) request.getAttribute("studentID");
%>
 <h3>Welcome to the Student Details Page</h3>
 <table border="1">
 	<tr>
 		<td>First Name</td>
 		<td><%=studentID%></td>
 	</tr>
 	<tr>
 		<td>First Name</td>
 		<td><%=studentBean.getFirstName() %></td>
 	</tr>
 	<tr>
 		<td>Last Name</td>
 		<td><%=studentBean.getLastName() %></td>
 	</tr>
 	<tr>
 		<td>Address</td>
 		<td><%=studentBean.getAddr() %></td>
 	</tr>
 	<tr>
 		<td>Zip Code</td>
 		<td><%=studentBean.getZipCode() %></td>
 	</tr>
 	<tr>
 		<td>City</td>
 		<td><%=studentBean.getCity()%></td>
 	</tr>
 	<tr>
 		<td>State</td>
 		<td><%=studentBean.getState()%></td>
 	</tr>
 	<tr>
 		<td>Tel No</td>
 		<td><%=studentBean.getTelno()%></td>
 	</tr>
 	<tr>
 		<td>Email ID</td>
 		<td><%=studentBean.getEmailID()%></td>
 	</tr>
 	<tr>
 		<td>High School Month</td>
 		<td><%=studentBean.getHighschoolMonth()%></td>
 	</tr>
 	<tr>
 		<td>High School Year</td>
 		<td><%=studentBean.getHighschoolYear()%></td>
 	</tr>
 	<tr>
 		<td>URL</td>
 		<td><%=studentBean.getUrl()%></td>
 	</tr>
 	<tr>
 		<td>Date of Survey</td>
 		<td><%=studentBean.getDateOfSurvey()%></td>
 	</tr>
 	<tr>
 		<td>Thing Liked on Campus</td>
 		<td><%=studentBean.getLikedUniv()%></td>
 	</tr>
 	<tr>
 		<td>Things Interested in GMU</td>
 		<td><%=studentBean.getInterestInGmu()%></td>
 	</tr>
 	<tr>
 		<td>Likelihood of Recommending University</td>
 		<td><%=studentBean.getLikelihoodOfRecommendingSchool()%></td>
 	</tr>
 	<tr>
 		<td>Additional Comments</td>
 		<td><%=studentBean.getAdditionalComments()%></td>
 	</tr>	
 </table>
</body>
</html>