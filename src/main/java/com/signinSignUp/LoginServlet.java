package com.signinSignUp;

import java.io.*;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Connection connection=null;
    	System.out.println("login");
        String email = request.getParameter("email");

        DatabaseHandler databaseHandler = null;
		try {
			databaseHandler = new DatabaseHandler();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (databaseHandler.userExists(email)) {
            response.getWriter().write("Login successful!");
        } else {
            response.getWriter().write("User does not exist. Please sign up.");
        }
    }
}
