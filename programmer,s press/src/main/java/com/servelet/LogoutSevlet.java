package com.servelet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techblog.entities.Message;

@WebServlet("/LogoutSevlet")
public class LogoutSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LogoutSevlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession s = request.getSession();

          s.removeAttribute("currentUser");

          Message m = new Message("Logout Successfully", "success", "alert-success");

          s.setAttribute("msg", m);

          response.sendRedirect("login_page.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
