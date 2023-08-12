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

public class FormServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		
		String FullName = request.getParameter("name");		
		String Place = request.getParameter("place");
		String PhoneNo = request.getParameter("phone");
		String BikeName = request.getParameter("bikename");
		String Testdrive = request.getParameter("drive");
		String Testdate = request.getParameter("date");
		
		RequestDispatcher dispatcher = null;
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","Password");
			PreparedStatement statement = connection.prepareStatement("insert into form(FullName, PhoneNo, Place, BikeName, Testdrive, Testdate) values(?,?,?,?,?,?) ");
			statement.setString(1, FullName);			
			statement.setString(2, PhoneNo);
			statement.setString(3, Place);
			statement.setString(4, BikeName);
			statement.setString(5, Testdrive);
			statement.setString(6, Testdate);
			
			int count = statement.executeUpdate();
			dispatcher = request.getRequestDispatcher("thank.html");
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
