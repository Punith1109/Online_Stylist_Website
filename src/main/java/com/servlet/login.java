package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
//		HttpsSession session=request.getSession();
//		RequestDispatcher dispatcher=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wdd1?useSSL=false","root","Punith2001");
			
			java.sql.PreparedStatement ps = connection.prepareStatement("select * from miniproject.signup where email=? and password=?;");
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
//				session.setAttribute("name" ,rs.getString("name"));
//				dispatcher=request.getRequestDispatcher("mpfirstpage.html");
				response.sendRedirect("mpfirstpage.html"); 
			}
			else {
//				session.setAttribute("status" ,"failed");
//				dispatcher=request.getRequestDispatcher("login.java");
				response.sendRedirect("loginfailed.html"); 
			}
//			dispatcher.forward(request, response);
			
			connection.close();
			
		}
		catch(SQLException | ClassNotFoundException e) {
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}