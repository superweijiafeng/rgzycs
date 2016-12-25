package com.fjw.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.fjw.dao.SQLHelper;
import com.fjw.domain.Cat;
import com.fjw.domain.Nav;

public class NavService {
	public static Nav getNav(int id) {
		Nav nav=new Nav();
		nav.setNavId(id);
		
		SQLHelper sqlHelper=new SQLHelper();
		String sql1="select title,image,content,timestamp from navContent where navId=?";
		Object[] params1={id};
		ArrayList<ArrayList<Object>> sqlResult1=sqlHelper.executeQuery(sql1, params1);
		if(sqlResult1.size()!=0) {
			nav.setTitle(sqlResult1.get(0).get(0).toString());
			nav.setImage(FileContentService.getFileContent((Integer)sqlResult1.get(0).get(1)));
			nav.setContent(sqlResult1.get(0).get(2).toString());
			nav.setTimestamp((Timestamp)sqlResult1.get(0).get(3));
		}
		
		String sql2="select id,catId,title,image from catContent where navId=?";
		ArrayList<ArrayList<Object>> sqlResult2=sqlHelper.executeQuery(sql2, params1);
		for(int i=0;i<sqlResult2.size();i++){
			Cat cat=new Cat();
			cat.setNavId(id);
			cat.setId((Integer)sqlResult2.get(i).get(0));
			cat.setCatId((Integer)sqlResult2.get(i).get(1));
			cat.setTitle((String)sqlResult2.get(i).get(2));
			cat.setImage(FileContentService.getFileContent((Integer)sqlResult2.get(i).get(3)));
			nav.addCat(cat);
		}
		
		return nav;
	}

	public static boolean modifyNav(Nav nav) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql="update navContent set title=?,image=?,content=?,timestamp=? where navId=?";
		Object params[]={nav.getTitle(),(nav.getImage()==null?null:nav.getImage().getId()),nav.getContent(),
						new Timestamp(new Date().getTime()),nav.getNavId()};
		return 1==sqlHelper.executeUpdate(sql, params);
	}
}
