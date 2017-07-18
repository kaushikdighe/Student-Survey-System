package com.swe642.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.swe642.beans.StudentBean;


/**
 * StudentDetailsServices does all database insertions and retrieval
 * */
public class StudentDetailsServices 
{
	protected Connection createDbConnection(String connectionString, String userName, String password) 
	{
			Connection con = null;
			System.out.println("------Creating Oracale DB connection-------");
			try 
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(connectionString, userName, password);
			} 
			catch (Exception e) 
			{
				System.out.println("------Error connecting-------");
				e.printStackTrace();
			} 
			return con;
	}
	
	public void InsertStudentDetails(StudentBean student,String connectionString,String userName,String password)
	{
		Connection conn = null;
		String insertQuery = "";
		System.out.println("-----------------In InsertStudentDetails function-------------------");
		try
		{
			conn = createDbConnection(connectionString, userName, password);
			/*String studentlike = "student";
			String studentunvi = "friends";*/
			insertQuery = "insert into swe642_StudentDetails "
						+ "VALUES('"+student.getStudentID()+"','"+student.getFirstName()+"','"+student.getLastName()+"',"
						+ "'"+student.getAddr()+"','"+student.getZipCode()+"','"+student.getCity()+"','"+student.getState()+"',"
						+ "'"+student.getTelno()+"','"+student.getEmailID()+"','"+student.getHighschoolMonth()+"','"+student.getHighschoolYear()+"',"
						+ "'"+student.getUrl()+"','"+student.getDateOfSurvey()+"','"+student.getLikedUniv()+"','"+student.getInterestInGmu()+"',"
						+ "'"+student.getLikelihoodOfRecommendingSchool()+"','"+student.getAdditionalComments()+"')";
			
			System.out.println("Insert Query is-----"+insertQuery);
			PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
			preparedStatement.executeUpdate();	
		}
		catch(Exception e)
		{
			System.out.println("-----Error in InsertStudentDetails Function------");
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				conn.close();
			} 
			catch(SQLException e) 
			{
				System.out.println("------Error closing connection in InsertStudentDetails Function------");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> getStudentID(String connectionString,String userName,String password)
	{
		Connection conn = null;
		String getStudentIDQuery = "";
		Statement statement = null;
		ArrayList<String> studentIDList = new ArrayList<String>();
		String studentID = "";
		System.out.println("-----------------In getStudentID function-------------------");
		
		try
		{
			conn = createDbConnection(connectionString, userName, password);
			statement = conn.createStatement();
			getStudentIDQuery = "SELECT DISTINCT(STUDENTID) FROM SWE642_STUDENTDETAILS ORDER BY STUDENTID ASC";
			ResultSet resultset = statement.executeQuery(getStudentIDQuery);
			while(resultset.next())
			{
				studentID = resultset.getString("STUDENTID");
				studentIDList.add(studentID);
			}
			resultset.close();
			System.out.println("ArrayList is--"+studentIDList.toString()+"---Length--"+studentIDList.size());
		}
		catch(Exception e)
		{
			System.out.println("-------Exception in getStudentID method------");
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				conn.close();
			} 
			catch(SQLException e) 
			{
				System.out.println("------Error closing connection in getStudentID Function------");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return studentIDList;
	}
	
	public StudentBean getStudentDeatils(String studentID,String connectionString,
											String userName,String password)
	{
		StudentBean studentBean = new StudentBean();
		Connection conn = null;
		String getStudentDetailsQuery = "";
		Statement statement = null;
		System.out.println("-----------------In getStudentDeatils function-------------------");
		try
		{
			conn = createDbConnection(connectionString, userName, password);
			statement = conn.createStatement();
			getStudentDetailsQuery = "SELECT * FROM SWE642_STUDENTDETAILS WHERE STUDENTID=\'" + studentID + "\'";
			ResultSet resultset = statement.executeQuery(getStudentDetailsQuery);
			while(resultset.next())
			{
				studentBean.setStudentID(studentID);
				studentBean.setFirstName(resultset.getString("FNAME"));
				studentBean.setLastName(resultset.getString("LNAME"));
				studentBean.setAddr(resultset.getString("ADDRESS"));
				studentBean.setZipCode(resultset.getString("ZIPCODE"));
				studentBean.setCity(resultset.getString("CITY"));
				studentBean.setState(resultset.getString("YOURSTATE"));
				studentBean.setTelno(resultset.getString("MOBILEPHONE"));
				studentBean.setEmailID(resultset.getString("EMAILID"));
				studentBean.setHighschoolMonth(resultset.getString("GRADMONTH"));
				studentBean.setHighschoolYear(resultset.getString("GRADYEAR"));
				studentBean.setUrl(resultset.getString("YOURURL"));
				studentBean.setDateOfSurvey(resultset.getString("DATEOFSURVEY"));
				studentBean.setLikedUniv(resultset.getString("THINGSLIKEDONCAMP"));
				studentBean.setInterestInGmu(resultset.getString("INTERESTINUNIVERSITY"));
				studentBean.setLikelihoodOfRecommendingSchool(resultset.getString("LIKELIHOODOFRECOMMEND"));
				studentBean.setAdditionalComments(resultset.getString("ADDITIONALCOMMENTS"));
					  
			}
			resultset.close();
		}
		catch(Exception e)
		{
			System.out.println("-------Error in getStudentDeatils------");
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				System.out.println("------Error closing connection in getStudentDeatils Function------");
				e.printStackTrace();
			}
		}
		return studentBean;
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		StudentDetailsServices sd = new StudentDetailsServices();
		sd.getStudentID("jdbc:oracle:thin:@apollo.vse.gmu.edu:1521:ite10g", "kdighe", "oorsij");
	}

}
