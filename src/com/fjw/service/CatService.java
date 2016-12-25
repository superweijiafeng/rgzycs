package com.fjw.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.fjw.dao.SQLHelper;
import com.fjw.domain.Art;
import com.fjw.domain.Cat;

public class CatService {
//	public static Cat getCat(int navId,int catId) {
//		return getCat(navId, catId, 0, 0);
//	}

	public static boolean modifyCat(Cat cat) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql="update catContent set navId=?,catId=?,title=?,image=?,content=?,timestamp=? where id=?";
		Object params[]={cat.getNavId(),cat.getCatId(),
						cat.getTitle(),(cat.getImage()==null?null:cat.getImage().getId()),cat.getContent(),
						new Timestamp(new Date().getTime()),cat.getId()};
		return 1==sqlHelper.executeUpdate(sql, params);
	}

	public static Cat createCat(Cat cat) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql1="select max(catId) from catContent where navId=?";
		Object params1[]={cat.getNavId()};
		ArrayList<ArrayList<Object>> sqlResult1=sqlHelper.executeQuery(sql1, params1,true,false);
		int maxCatId=0;
		if(sqlResult1.size()!=0 && sqlResult1.get(0).size()!=0 && sqlResult1.get(0).get(0)!=null) {
			maxCatId=(Integer)sqlResult1.get(0).get(0);
		}
		cat.setCatId(maxCatId+1);
		
		String sql2="insert into catContent (navId,catId,title,image,content,timestamp) values (?,?,?,?,?,?)";
		Object param2[]={cat.getNavId(),cat.getCatId(),
							cat.getTitle(),(cat.getImage()==null?null:cat.getImage().getId()),cat.getContent(),new Timestamp(new Date().getTime())};
		if(1!=sqlHelper.executeUpdate(sql2, param2,false,false)) {
			return null;
		}
		String sql3="select LAST_INSERT_ID()";
		ArrayList<ArrayList<Object>> sqlResult3=sqlHelper.executeQuery(sql3, null,false,true);
		cat.setId(((BigInteger)sqlResult3.get(0).get(0)).intValue());
		
		return cat;
	}

	public static boolean deleteCat(int navId, int catId) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql1="delete from artContent where navId=? and catId=?";
		Object[] params1={navId,catId};
		sqlHelper.executeUpdate(sql1, params1);
		
		String sql2="delete from catContent where navId=? and catId=?";
		return 1==sqlHelper.executeUpdate(sql2, params1);
	}

	public static Cat getCat(int navId, int catId, int artPerPage, int page) {
		Cat cat=null;
		
		SQLHelper sqlHelper=new SQLHelper();
		String sql1="select id,title,image,content,timestamp from catContent where navId=? and catId=?";
		Object[] params1={navId,catId};
		ArrayList<ArrayList<Object>> sqlResult1=sqlHelper.executeQuery(sql1, params1);
		if(sqlResult1.size()!=0) {
			cat=new Cat();
			cat.setNavId(navId);
			cat.setCatId(catId);
			cat.setId((Integer)sqlResult1.get(0).get(0));
			cat.setTitle(sqlResult1.get(0).get(1).toString());
			cat.setImage(FileContentService.getFileContent((Integer)sqlResult1.get(0).get(2)));
			cat.setContent(sqlResult1.get(0).get(3).toString());
			cat.setTimestamp((Timestamp)sqlResult1.get(0).get(4));
		}
		
		if(artPerPage!=0) {
			String sql2=null;
			Object[] params2=null;
			
			sql2="select id,artId,title,image from artContent where navId=? and catId=? order by id limit ?,?";
			params2=new Object[]{navId,catId,artPerPage*(page-1),artPerPage};
			ArrayList<ArrayList<Object>> sqlResult2=sqlHelper.executeQuery(sql2, params2);
			for(int i=0;i<sqlResult2.size();i++){
				Art art=new Art();
				art.setNavId(cat.getNavId());
				art.setCatId(cat.getCatId());
				art.setId((Integer)sqlResult2.get(i).get(0));
				art.setArtId((Integer)sqlResult2.get(i).get(1));
				art.setTitle((String)sqlResult2.get(i).get(2));
				art.setImage(FileContentService.getFileContent((Integer)sqlResult2.get(i).get(3)));
				cat.addArt(art);
			}
		}
		return cat;
	}

	public static int getNArts(int navId, int catId) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql="select count(*) from artContent where navId=? and catId=?";
		Object[] params={navId,catId};
		ArrayList<ArrayList<Object>> sqlResult=sqlHelper.executeQuery(sql, params);
		if(sqlResult!=null && sqlResult.size()>0 && sqlResult.get(0).size()>0) {
			return ((Long)sqlResult.get(0).get(0)).intValue();
		} else {
			return 0;
		}
	}
}
