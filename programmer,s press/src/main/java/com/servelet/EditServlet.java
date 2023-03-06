package com.servelet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.UserDao;
import com.helper.ConnectionProvider;
import com.helper.Helper;
import com.techblog.entities.Message;
import com.techblog.entities.User;

/**
 * Servlet implementation class EditServlet
 */
@MultipartConfig
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 String userEmail=request.getParameter("user_email");
		 String userName=request.getParameter("user_name");
		 String userPassword=request.getParameter("user_password");
		 String userabout=request.getParameter("user_about");
		 Part part=request.getPart("image");
		 String imageName=part.getSubmittedFileName();
		 //get the user from  the seession
		 HttpSession s=request.getSession();
		User user =(User) s.getAttribute("currentUser");
		user.setEmail(userEmail);
		user.setName(userName);
		user.setPassword(userPassword);
		user.setAbout(userabout);
		String oldFile=user.getProfile();
		user.setProfile(imageName);
		//update in database
		UserDao dao=new UserDao(ConnectionProvider.getconnection());
		boolean ans=dao.updateUser(user);
		  if (ans) {

           
              //start of photo work
              //delete code
              String path= request.getRealPath("/") + "pics" + File.separator+  user.getProfile();

              String pathOldFile = request.getRealPath("/") + "pics" + File.separator + oldFile;

              if (!oldFile.equals("images.png")) {
                  Helper.deleteFile(pathOldFile);
              }
              if (Helper.SaveFile(part.getInputStream(), path)) {
                  out.println("Profile updated...");
                  Message msg = new Message("Profile details updated...", "success", "alert-success");
                  s.setAttribute("msg", msg);

              } else {
                  //////////////
                  Message msg = new Message("Something went wrong..", "error", "alert-danger");
                  s.setAttribute("msg", msg);
              }

              //end of phots work
          } else {
              out.println("not updated..");
              Message msg = new Message("Something went wrong..", "error", "alert-danger");
              s.setAttribute("msg", msg);

          }

          response.sendRedirect("profile.jsp");
		}

}
