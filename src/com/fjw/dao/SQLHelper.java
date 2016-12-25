package com.fjw.dao;

import java.util.ArrayList;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import com.sina.sae.util.SaeUserInfo;

public class SQLHelper {

	Connection ct=null;
	PreparedStatement psm=null;
	ResultSet rs=null;
	
	static String database;
	static String username;
	static String password;
	
	static Properties pp=null;
	static{
		if(SaeUserInfo.getMcIp().equals("127.0.0.1")) {
			InputStream is=null;
			pp=new Properties();
			try {
				is=SQLHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
				pp.load(is);
				Class.forName(pp.getProperty("driver"));
				database=pp.getProperty("database");
				username=pp.getProperty("username");
				password=pp.getProperty("password");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if(is!=null) is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			database="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_rgzycs";
			username=SaeUserInfo.getAccessKey();
			password=SaeUserInfo.getSecretKey();
		}
	}
	
	public ArrayList< ArrayList<Object> > executeQuery(String sql, Object[] params) {
		return this.executeQuery(sql, params, true, true);
	}
	
	public ArrayList< ArrayList<Object> > executeQuery(String sql, Object[] params,boolean isGetConn,boolean isCloseConn) {	
		
		ArrayList< ArrayList<Object> > result=new ArrayList< ArrayList<Object> >();
		
		try{
			if(isGetConn==true) {
				ct=DriverManager.getConnection(database,username,password);
			}
			psm=ct.prepareStatement(sql);
			if(params!=null) {
				for(int i=0;i<params.length;i++){
					psm.setObject(i+1, params[i]);
				}
			}
			rs=psm.executeQuery();
			int colnum=rs.getMetaData().getColumnCount();
			while(rs.next()==true) {
				ArrayList<Object> tmp=new ArrayList<Object>();
				for(int i=1;i<=colnum;i++) {
					tmp.add(rs.getObject(i));
				}
				result.add(tmp);
			}
		} catch(SQLException e){
			e.printStackTrace();
	//		ArrayList<Object> tmp=new ArrayList<Object>();
	//		tmp.add(e);
	//		result.add(tmp);
		} finally {
			if(isCloseConn==true) {
				close();
			}
		}
		return result;
	}
	
	
public ArrayList< ArrayList<Object> > executeQueryTmp(String sql, Object[] params,boolean isGetConn,boolean isCloseConn) {	
		
		ArrayList< ArrayList<Object> > result=new ArrayList< ArrayList<Object> >();
		
		try{
			if(isGetConn==true) {
				ct=DriverManager.getConnection(database,username,password);
			}
			psm=ct.prepareStatement(sql);
			if(params!=null) {
				for(int i=0;i<params.length;i++){
					psm.setObject(i+1, params[i]);
				}
			}
			rs=psm.executeQuery();
			int colnum=rs.getMetaData().getColumnCount();
			while(rs.next()==true) {
				ArrayList<Object> tmp=new ArrayList<Object>();
				for(int i=1;i<=colnum;i++) {
					tmp.add(rs.getObject(i));
				}
				result.add(tmp);
			}
		} catch(SQLException e){
			e.printStackTrace();
			ArrayList<Object> tmp=new ArrayList<Object>();
			tmp.add(e);
			result.add(tmp);
		} finally {
			if(isCloseConn==true) {
				close();
			}
		}
		return result;
	}
	
	
	
	public int executeUpdate(String sql, Object[] params) {
		return this.executeUpdate(sql, params,true,true);
	}
	
	public int executeUpdate(String sql, Object[] params,boolean isGetConn,boolean isCloseConn) {
		int retVal=0;
		try{
			if(isGetConn==true) {
				ct=DriverManager.getConnection(database,username,password);
			}
			psm=ct.prepareStatement(sql);
			if(params!=null) {
				for(int i=0;i<params.length;i++){
					psm.setObject(i+1, params[i]);
				}
			}
			retVal=psm.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			retVal=0;
		} finally {
			if(isCloseConn==true) {
				this.close();
			}
		}
		return retVal;
	}
	
	public boolean executeUpdate(String[] sql, Object[][] params){
		return this.executeUpdate(sql, params,true,true);
	}
	
	public boolean executeUpdate(String[] sql, Object[][] params, boolean isGetConn,boolean isCloseConn) {
		if(sql.length!=params.length) {
			return false;
		}
		try{
			if(isGetConn==true) {
				ct=DriverManager.getConnection(database,username,password);
			}
			ct.setAutoCommit(false);
			for(int i=0;i<sql.length;i++) {
				try{
					psm=ct.prepareStatement(sql[i]);
					if(params[i]!=null) {
						for(int j=0;j<params[i].length;j++){
							psm.setObject(j+1, params[i][j]);
						}
					}
					psm.executeUpdate();
				} catch(SQLException e){
					e.printStackTrace();
					ct.rollback();
					return false;
				} finally {
					if(psm!=null) psm.close();
				}
			}
			ct.commit();
		} catch(SQLException e){
			e.printStackTrace();
			try {
				ct.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			if(isCloseConn==true) {
				this.close();
			}
		}
		return true;
	}
	
	public boolean execute(String sql, Object[] params){
		return this.execute(sql, params,true,true);
	}
	
	public boolean execute(String sql, Object[] params, boolean isGetConn,boolean isCloseConn) {
		try{
			if(isGetConn==true) {
				ct=DriverManager.getConnection(database,username,password);
			}
			psm=ct.prepareStatement(sql);
			if(params!=null) {
				for(int i=0;i<params.length;i++){
					psm.setObject(i+1, params[i]);
				}
			}
			return psm.execute();
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally {
			if(isCloseConn==true) {
				this.close();
			}
		}
	}

	public void close() {
		try {
			if(rs!=null) rs.close();
			if(psm!=null) psm.close();
			if(ct!=null) ct.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}