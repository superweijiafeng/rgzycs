package com.fjw.service;

import java.util.ArrayList;

import com.fjw.dao.SQLHelper;
import com.fjw.domain.User;

public class UserService {
	public static User checkPassword(User user){
		SQLHelper sqlHelper=new SQLHelper();
		String sql="select username,password from admin where username=? and password=sha(?)";
		Object[] params={user.getUsername(),user.getPassword()};
		ArrayList<ArrayList<Object>> sqlResult=sqlHelper.executeQuery(sql, params);
		if(sqlResult.size()==1){
			return user;
		} else {
			return null;
		}
	}

	public static User changePassword(User newUser) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql="update admin set password=sha(?) where username=?";
		Object[] params={newUser.getPassword(),newUser.getUsername()};
		int sqlVal=sqlHelper.executeUpdate(sql, params);
		if(sqlVal==1){
			return newUser;
		} else {
			return null;
		}
	}
}
