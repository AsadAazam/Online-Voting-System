package com.asad;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/AdminDuty")
public class AdminDuty extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		
		try
		{
			HttpSession session=request.getSession();
			Integer status = (Integer) session.getAttribute("login");
			if(status==1)
			{
				
			
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
		//pw.println("Asad");
		int choice = Integer.parseInt(request.getParameter("click"));
		//pw.println(choice);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/vote", "root", "Vampire21");
		Statement s = c.createStatement();
		
		switch (choice)
		{
		case 1:
			response.sendRedirect("Add.html");
		break;
		
		case 2:
			pw.println("<a href=\"AdminUse.html\">Go Back to Add More</a>");
		break;
		
		case 3:
			int x=s.executeUpdate("update candidates set Votes='0'");
			//System.out.println(x);
			int y=s.executeUpdate("update user set Given='N'");
             //System.out.println(y);
			pw.println("<h1>All votes set to zero<h1>");
			
		break;
		
		case 4:
			ResultSet rs4=s.executeQuery("Select * from user");
			pw.println("<feildSet style=\"margin: 0 auto; \r\n" + 
					"width:250px;\">");
			pw.println("<table>");
			while(rs4.next())
			{   
			//System.out.println(p);
				pw.println("<tr><td>"+rs4.getString("Name")+"</td><td><a href=deleteUser?id=1&click="+rs4.getString("Name")+"><img src=\"image/delete.jpg\" width=\"200\" height=\"100\"></a>");
			}
			pw.println("</table>");
			pw.println("</feildSet >");
			pw.println("<a href=\"AdminUse.html\">Go Back </a>");
		break;
		
		case 5:
			ResultSet rs3=s.executeQuery("Select * from candidates");
			pw.println("<feildSet style=\"margin: 0 auto; \r\n" + 
					"width:250px;\">");
			pw.println("<table>");
			while(rs3.next())
			{   int p=rs3.getInt("Sno");
			//System.out.println(p);
				pw.println("<tr><td>"+rs3.getString("Name")+"</td><td><a href=delete?id=1&click="+p+"><img src=\"image/delete.jpg\" width=\"200\" height=\"100\"></a>");
			}
			pw.println("</table>");
			pw.println("</feildSet >");
			pw.println("<a href=\"AdminUse.html\">Go Back</a>");
			
		break;
		
		case 6:
			ResultSet rs =s.executeQuery("select name, votes from candidates where votes ="
					+ "(select max(votes) from candidates)");
			while(rs.next())
			{
				pw.println("<table border =1 style=\"margin: 0 auto; \r\n" + 
						"width:250px;\"><tr><td>"+rs.getString("Name")+"</td><td>"+rs.getInt("votes")+"</td><tr>");
			}
			pw.println("</table>");
			pw.println("<a href=\"AdminUse.html\">Go Back</a>");
			break;
		
		case 7:
			ResultSet rs1= s.executeQuery("Select Name, Votes from Candidates ");
			while(rs1.next())
			{
				pw.println("<table border =1 style=\"margin: 0 auto; \r\n" + 
						"width:250px;\"><tr><td>"+rs1.getString("Name")+"</td><td>"+rs1.getInt("votes")+"</td><tr>");
			}
			pw.println("</table>");
			pw.println("<a href=\"AdminUse.html\">Go Back </a>");
			break;
		
		}
			}
			else
			{
				response.sendRedirect("Admin.html");;
			}
		}
		catch (Exception e)
		{ System.out.println("Exception="+e);
			response.sendRedirect("Admin.html");
		}
		
		
	}

}
