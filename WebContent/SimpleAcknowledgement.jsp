<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.swe642.beans.DataBean"%>
<%@page import="com.swe642.beans.StudentBean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple Acknowledgement JSP</title>
<style type="text/css">

			.top
			{
    			width: 100%;
	    		padding: 13px;
	    		padding-top: 23px;  
	    		border: 0;
	    		margin: 0;
			    background-color: #78AB46;
			    border-bottom: 1px #006633;
			    text-align: center;
			}
			.heading-font
			{
				color: #000080;
				text-shadow: -1px 1px #FFFFFF;
			}
			.body-margin
			{
				margin: 0;
				background-color: #eeeeee;
			}
			.main
			{
    			width:776px;
			}
			.submit-button
			{
			    padding:5px 15px; 
			    background:#ccc; 
			    border:0 none;
			    /*-webkit-border-radius: 5px;*/
			    border-radius: 5px; 
			}
			.low
			{
				position: absolute;
				right: 10px;
				bottom: -600px;
			}
			.data 
			{
    			width: 257px;
			}
			.error
			{
				color: red
			}
	</style>

</head>
<body class="body-margin">
<%
		StudentBean studentBean = (StudentBean) session.getAttribute("studentBean");
		DataBean databean = (DataBean) session.getAttribute("dataBean");
		ArrayList<String> studentList = (ArrayList<String>) session.getAttribute("studentIDList");
		System.out.println("Mean in JSP------>"+databean.getMean());
%>
	<div class="top">
			<h1 class="heading-font">Department of Computer Science</h1>
			<h4 class="heading-font">Volgenau School of Engineering</h4>
			<h4 class="heading-font">Dighe</h4>
	</div>
	<h3>Simple Acknowledgement</h3>
	<h4>Thank You,<%= studentBean.getFirstName() %> <%= studentBean.getLastName() %> to fill out the form</h4>
	<h4>Mean: <%= databean.getMean() %></h4>
	<h4>Standard Deviation: <%= databean.getStandardDeviation() %></h4>
	<h4>Information successfully saved </h4><hr>

<ul>
 <% for(int i = 0; i < studentList.size(); i+=1) { %>
 	<li><a name="studentlist" href="/SWE642_Assignment4/StudentDAO?id=<%=studentList.get(i)%>"><%= studentList.get(i) %></a></li> 
 <%} %>
</ul>
</body>
</html>