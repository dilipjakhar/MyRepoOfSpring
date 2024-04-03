package com.signinSignUp;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Connection connection=null;
	   	 DatabaseHandler databaseHandler = null;
	       String userName = req.getParameter("userName");
	       String email = req.getParameter("email");
	       int age = Integer.parseInt(req.getParameter("age"));
	       String gender = req.getParameter("gender");
	       int weight = Integer.parseInt(req.getParameter("weight"));
	       int hight = Integer.parseInt(req.getParameter("hight"));
	          
	       System.out.println(userName);
	      
			try {
				
				databaseHandler = new DatabaseHandler();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	       if (databaseHandler.userExists(email)) {
	           resp.getWriter().write("Email already exists. Please use a different email.");
	       } else {
	           if (databaseHandler.createUser(userName, email, age, gender, weight, hight)) {
	               resp.getWriter().write("Signup successful!");
	           } else {
	               resp.getWriter().write("Signup failed. Please try again.");
	           }
	       }
	}

}
