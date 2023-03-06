package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.techblog.entities.User;

public class UserDao {
	private Connection con;
	public UserDao(Connection con) {
		this.con=con;
}
public boolean saveUser(User user) {
	boolean f=false;
	try {
		
		String query="insert into users(name,email,password,gender,about) values(?,?,?,?,?)";
	PreparedStatement pstmnt = this.con.prepareStatement(query);
	pstmnt.setString(1, user.getName());
	pstmnt.setString(2, user.getEmail());
	pstmnt.setString(3, user.getPassword());
	pstmnt.setString(4, user.getGender());
	pstmnt.setString(5, user.getAbout());
	pstmnt.executeUpdate();
	f=true;
	}catch(Exception e) {
		e.printStackTrace();
	}
	return f;
}
//get user email and user pasword
public User getUserEmailAndPassword(String email,String password) {
	User user=null;
	try {
		String query="select*from users where email=? and password=?";
		PreparedStatement pstmnt=con.prepareStatement(query);
		pstmnt.setString(1, email);
		pstmnt.setString(2, password);
		ResultSet set = pstmnt.executeQuery();
	if(set.next()) {
		user=new User();
		String name=set.getString("name");
	user.setName(name);
	user.setId(set.getInt("id"));
	user.setEmail(set.getString("email"));
	user.setPassword(set.getString("password"));
	user.setGender(set.getString("gender"));
	user.setAbout(set.getString("about") );
	user.setProfile(set.getString("profile"));
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	return user;
}
public boolean updateUser(User user) {
	boolean f=true;
	try {
		String query="update users set name=?,email=?,password=?,gender=?,about=?,profile=? where id=?";
				PreparedStatement p=con.prepareStatement(query);
				p.setString(1,user.getName());
				p.setString(2,user.getEmail());
				p.setString(3,user.getPassword());
				p.setString(4,user.getGender());
				p.setString(5,user.getAbout());
				p.setString(6,user.getProfile());
				p.setInt(7,user.getId());
				p.executeUpdate();
				f=true;
	}catch(Exception e) {
		e.printStackTrace();
	}
	return f;
}
}
