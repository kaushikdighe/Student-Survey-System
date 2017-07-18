package com.swe642.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.swe642.beans.DataBean;
import com.swe642.beans.StudentBean;
import com.swe642.services.DataFieldServices;
import com.swe642.services.StudentDetailsServices;

/**
 * Servlet implementation class StudentDAO
 */
/*@WebServlet(description = "Send Retrieve Data", urlPatterns = { "/StudentDAO" })*/

public class StudentDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	String connectionString = "jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g";
	String userName = "kdighe";
	String password = "oorsij";
	
    public StudentDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		StudentDetailsServices studentDetails_Services =  new StudentDetailsServices();
		StudentBean stud_bean = new StudentBean();
		RequestDispatcher request_Dispatcher;
		HttpSession httpSession = request.getSession();
		String address = "";
		
		System.out.println("-----------------------In doGet--------------------------------");
		
		String studentID = request.getParameter("id").toString();
		System.out.println("StudentID in doGet is----->"+studentID);
		
		if(studentID != null && studentID != "")
		{
			address = "studentdetail.jsp";
			stud_bean = studentDetails_Services.getStudentDeatils(studentID, connectionString, userName, password);
			httpSession.setAttribute("studentDetailsBean", stud_bean);
			request.setAttribute("studentID", studentID);
			request_Dispatcher = request.getRequestDispatcher(address);
			request_Dispatcher.forward(request, response);
		}
		else
		{
			address = "nosuchstudent.jsp";
			request_Dispatcher = request.getRequestDispatcher(address);
			request_Dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		StudentDetailsServices studentDetailsServices =  new StudentDetailsServices();
		DataFieldServices datafield = new DataFieldServices();
		StudentBean studbean = new StudentBean();
		DataBean databean = new DataBean();
		RequestDispatcher requestDispatcher;
		String address = "";
		
		System.out.println("------------------------In doPost---------------------------");
		 
		studbean = getStudentInfo(request);
		String data = request.getParameter("data").toString();
		System.out.println("----in doPost----"+data);
		
		databean = datafield.setDataBean(data);
		float mean = databean.getMean();

		if(mean > 90.0)
		{
			address = "WinnerAcknowledgement.jsp";
		}
		else
		{
			address = "SimpleAcknowledgement.jsp";
		}
		HttpSession session = request.getSession();
		session.setAttribute("studentBean", studbean);
		session.setAttribute("dataBean", databean);
		
		studentDetailsServices.InsertStudentDetails(studbean,connectionString,userName,password);
		ArrayList<String> studentIDList = studentDetailsServices.getStudentID(connectionString, userName, password);
		System.out.println("---Student List is-----"+studentIDList.toString());
		System.out.println("---Student List Length-----"+studentIDList.size());
		
		session.setAttribute("studentIDList", studentIDList);
		
		requestDispatcher = request.getRequestDispatcher(address);
		requestDispatcher.forward(request, response);
		
		//System.out.println(studbean.getAdditionalComments().toString());
		//pw.println("Registered Successfully");	
	}
	
	public StudentBean getStudentInfo(HttpServletRequest request)
	{
		StudentBean studentBean = new StudentBean();
		System.out.println("------------------------In get StudentInfo function-------------------");
		try
		{		
			 
			String studentID = request.getParameter("sid").toString();
			String firstName = request.getParameter("fid").toString();
			String lastName = request.getParameter("lid").toString();
			String addr = request.getParameter("addr1").toString();
			String zipCode = request.getParameter("zip").toString(); 	
			String city = request.getParameter("city").toString();
			String state = request.getParameter("state").toString();
			String telno = request.getParameter("telephone").toString();
			String emailID = request.getParameter("emailid").toString();
			String highschoolMonth = request.getParameter("months").toString();
			String highschoolYear = request.getParameter("year").toString();
			String url = request.getParameter("url").toString();
			String dateOfSurvey = request.getParameter("dateforsurvey").toString();
			String likelihoodOfRecommendingSchool = request.getParameter("myselect").toString();
			String additionalComments = request.getParameter("addcomments").toString();
			String interestInGmu = request.getParameter("knowuni").toString();
			
			String[] thingsyouliked = request.getParameterValues("thingsyouliked");
			String likedUniv = "";
			for(int i = 0 ; i < thingsyouliked.length ; i++)
			{
				if(likedUniv==null || likedUniv=="")
				{
					likedUniv = thingsyouliked[i];
				}
				else
				{
					likedUniv = likedUniv + ","+ thingsyouliked[i];
				}
			}
			//System.out.println(likedUniv);
			
			studentBean.setStudentID(studentID);
			studentBean.setFirstName(firstName);
			studentBean.setLastName(lastName);
			studentBean.setAddr(addr);
			studentBean.setZipCode(zipCode);
			studentBean.setCity(city);
			studentBean.setState(state);
			studentBean.setTelno(telno);
			studentBean.setEmailID(emailID);
			studentBean.setHighschoolMonth(highschoolMonth);
			studentBean.setHighschoolYear(highschoolYear);
			studentBean.setUrl(url);
			studentBean.setDateOfSurvey(dateOfSurvey);
			studentBean.setLikelihoodOfRecommendingSchool(likelihoodOfRecommendingSchool);
			studentBean.setAdditionalComments(additionalComments);
			studentBean.setInterestInGmu(interestInGmu);
			studentBean.setLikedUniv(likedUniv);
			
			//System.out.println("studentID--"+studentID+"--firstName--"+firstName+"--lastName--"+lastName+"--addr--"+addr);
			//System.out.println("Student ID----"+studentBean.getStudentID().toString());
		}
		catch(Exception e)
		{
			System.out.println("---------Error in getStudentInfo function-----");
			e.printStackTrace();
		}
		return studentBean;
	}

}
