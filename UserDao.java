package com.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.usermanagement.bean.User;

public class UserDao 
{
	private String jdbcUrl = "jdbc:mysql://localhost:3306/userdb?useSSl=false";
	private String jdbcUsername = "root";
	private String jdbcPasswd = "Gula@7860";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_SQL_USER = "insert into users " + " (name, email, country, password) values" + " (?, ?, ?, ?);"; 
	private static final String SELECT_USER_BY_ID = " select id, , name, email, country,password from users where id = ?";
	private static final String SELECT_ALL_USERS = " select * from users";
	private static final String DELETE_USER_FROM_SQL = " delete from users where id =?";
	private static final String UPDATE_USER_SQL = " update users set name = ?, email =? , country = ?, password =? where id = ?";
	
	
	public UserDao()
	{
		
	}
	
	protected Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPasswd);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	
// insert user 
	public void insertUser(User user)
	{
		System.out.println(INSERT_SQL_USER);
		try( Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_SQL_USER);)
		{
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());
			ps.setString(4, user.getPassword());
			
			System.out.println(ps);
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			printSQLException(e);
		}
	}
	
	private void printSQLException( SQLException ex)
	{
		for( Throwable e : ex)
		{
			if(e instanceof SQLException)
			{
				e.printStackTrace(System.err);
				System.err.println(" SQL State : " + ((SQLException) e).getSQLState());
				System.err.println("SQL Error : " + (( SQLException) e).getErrorCode());
				System.err.println(" Message" + e.getMessage());
				Throwable t = ex.getCause();
				while(t != null)
				{
					System.out.println("Cause : " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	
//	select user by id
	
	public User selectUserById(int id)
	{
		User user = null;
//		Established a connection
		try( Connection con = getConnection();
//				Create a statement using connection object
				PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID);)
		{
			ps.setInt(1, id);
			System.out.println(ps);
//			Execute query or update query
			ResultSet result = ps.executeQuery();
//			process the resultset object
			while(result.next())
			{
				String name = result.getString("name");
				String email = result.getString("email");
				String country = result.getString("country");
				String password = result.getString("password");
				user = new User(id, name, email, country, password);
			}
		}
		catch(SQLException e)
		{
			printSQLException(e);
		}
		
		return user;
	}
//	select all users
	
	public List<User> selectAllUsers()
	{
		System.out.println(" Hello all users");
		List<User> users = new ArrayList<>();
		try( Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_ALL_USERS);)
		{
			System.out.println(ps);
			ResultSet result = ps.executeQuery();
			
			while(result.next())
			{
				int id = result.getInt("id");
				String name = result.getString("name");
				String email = result.getString("email");
				String country = result.getString("country");
				String password = result.getString("password");
				users.add(new User(id, name, email, country, password));
			}
		}
		catch(SQLException e)
		{
			printSQLException(e);
		}
		
		return users;
	}
//	delete user
	
	public boolean deleteUser(int id) throws SQLException
	{
		boolean rowDeleted;
		try( Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_USER_FROM_SQL);)
		{
			System.out.println(" Delete user : " + ps);
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate() > 0;
		}
		
		return rowDeleted;
	}
	
//	update user
	
	public boolean updateUser(User user) throws SQLException
	{
		boolean rowUpdated;
		try( Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_USER_SQL);)
		{
			System.out.println(" Update user : " + ps);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getId());
			rowUpdated = ps.executeUpdate() > 0;
			
		}		
		return rowUpdated;
	}
	
	
	
	
	
}
