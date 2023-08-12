package org.sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {


		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		RequestDispatcher dispatcher = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Password");
			PreparedStatement statement = connection.prepareStatement("select * from info where username = ? and pass = ?");
			statement.setString(1, username);			
			statement.setString(2, pass);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				dispatcher = request.getRequestDispatcher("brands.html");
			}else{
				request.getRequestDispatcher("login.html");
			}
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
