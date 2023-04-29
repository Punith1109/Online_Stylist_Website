package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signin
 */
@WebServlet("/signin")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw =response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String birthdate=request.getParameter("dob");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		RequestDispatcher dispatcher=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject?useSSL=false","root","Punith2001");
			
			java.sql.PreparedStatement ps = connection.prepareStatement("INSERT INTO `miniproject` .`signup` (`name`,`email`,`password`,`gender`,`date`,`address`,`phone`) VALUES (?,?,?,?,?,?,?);");
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, gender);
			ps.setString(5, birthdate);
			ps.setString(6, address);
			ps.setString(7, phone);
			int row = ps.executeUpdate();
//			dispatcher = request.getRequestDispatcher("signin.jsp");
	        if (row > 0) {
//	        	request.setAttribute("status","success");
	        	response.sendRedirect("Loginorsignup.html"); 
	        	
	        }
	        else {
//	        	request.setAttribute("status" , "failed");
	        	response.sendRedirect("signup.html"); 
	        }
//	        dispatcher.forward(request, response);
			connection.close();
		}
		catch(SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}