package com.usermanagement.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.usermanagement.bean.User;
import com.usermanagement.dao.UserDao;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
       
  
	public void init(ServletConfig config) throws ServletException 
	{
		userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getServletPath();
		switch(action)
		{
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert": 
			insertUser(request, response);
			break;
		case "/delete":
			deleteUser(request, response);
			break;
		case "/edit": 
			showEditForm(request, response);
			break;
		case "/update": 
			try {
				updateUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default: 
			listUser(request, response);
			break;
		}
		
	}




//	Insert user
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String password = request.getParameter("password");
		User newUser = new User(name, email, country, password);
		userDao.insertUser(newUser);
		response.sendRedirect("user-list.jsp");
		
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
		rd.forward(request, response);
	}

	
//	delete user
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
	  int id = Integer.parseInt(request.getParameter("id"));
	  try
	  {
		  userDao.deleteUser(id);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  response.sendRedirect("user-list.jsp");
	}

//	edit user
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser;
		try
		{
			existingUser = userDao.selectUserById(id);
			RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
			request.setAttribute("user", existingUser);
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


//	update user
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String password = request.getParameter("password");
		
		User user = new User(id, name, email, country, password);
		userDao.updateUser(user);
		response.sendRedirect("user-list.jsp");
		
	}

	
//	Show All Users (default)
private void listUser(HttpServletRequest request, HttpServletResponse response) {
	try
	{
		List<User> listUser = userDao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
		rd.forward(request, response);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
