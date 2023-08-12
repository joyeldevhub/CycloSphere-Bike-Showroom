package org.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;



public class RegisterServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		String FullName = request.getParameter("name");		
		String Place = request.getParameter("place");
		String PhoneNo = request.getParameter("phone");
		String Email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String username = request.getParameter("username");
		RequestDispatcher dispatcher = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Password");
			PreparedStatement statement = connection.prepareStatement("insert into Info(FullName, PhoneNo, Place, Email, pass, username) values(?,?,?,?,?,?) ");
			statement.setString(1, FullName);			
			statement.setString(2, PhoneNo);
			statement.setString(3, Place);
			statement.setString(4, Email);
			statement.setString(5, pass);
			statement.setString(6, username);
			
			int count = statement.executeUpdate();
			dispatcher = request.getRequestDispatcher("login.html");
			if(count > 0){
				request.setAttribute("status", "Success");				
			}else{
				request.setAttribute("status", "Failed");
			}
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}

	}

}
