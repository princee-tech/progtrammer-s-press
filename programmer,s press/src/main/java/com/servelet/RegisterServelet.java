package com.servelet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.techblog.entities.User;
import com.helper.ConnectionProvider;

/**
 * Servlet implementation class RegisterServelet
 */
@MultipartConfig
@WebServlet("/register")
public class RegisterServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		String check=request.getParameter("check");
		if(check==null)
		{
			out.println("box not checked");
		}
		else {
			String name=request.getParameter("user_name");
			String email=request.getParameter("user_email");
			String password=request.getParameter("user_password");
			String gender=request.getParameter("gender");
			String about=request.getParameter("about");
		User user=new User(name,email,password,gender,about);
			UserDao dao=new UserDao(ConnectionProvider.getconnection());
		if(dao.saveUser(user)) {
			out.println("done");
		}else {
			out.println("error");
		}
		}
	}

}
