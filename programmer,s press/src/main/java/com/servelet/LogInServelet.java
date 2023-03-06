package com.servelet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.techblog.entities.Message;
import com.techblog.entities.User;
import com.helper.ConnectionProvider;

/**
 * Servlet implementation class LogInServelet
 */
@WebServlet("/LogInServelet")
public class LogInServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LogInServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 String userEmail=request.getParameter("email");
		 String userPassword=request.getParameter("password");
		 UserDao dao = new UserDao(ConnectionProvider.getconnection());

         User u = dao.getUserEmailAndPassword(userEmail, userPassword);

         if (u == null) {
             //login.................
//             error///
//             out.println("Invalid Details..try again");
             Message msg = new Message("Invalid Details ! try with another", "error", "alert-danger");
             HttpSession s = request.getSession();
             s.setAttribute("msg", msg);

             response.sendRedirect("login_page.jsp");
	}else {
		HttpSession s=request.getSession();
	s.setAttribute("currentUser", u);
	response.sendRedirect("profile.jsp");
	}
	}

}
