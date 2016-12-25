package com.fjw.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.fjw.dao.SQLHelper;
import com.fjw.domain.Art;

public class ArtService {
	public static Art getArt(int navId,int catId,int artId) {
		Art art=null;
		
		SQLHelper sqlHelper=new SQLHelper();
		String sql1="select id,title,image,content,timestamp from artContent where navId=? and catId=? and artId=?";
		Object[] params1={navId,catId,artId};
		ArrayList<ArrayList<Object>> sqlResult1=sqlHelper.executeQuery(sql1, params1);
		if(sqlResult1.size()!=0) {
			art=new Art();
			art.setNavId(navId);
			art.setCatId(catId);
			art.setArtId(artId);
			art.setId((Integer)sqlResult1.get(0).get(0));
			art.setTitle(sqlResult1.get(0).get(1).toString());
			art.setImage(FileContentService.getFileContent((Integer)sqlResult1.get(0).get(2)));
			art.setContent(sqlResult1.get(0).get(3).toString());
			art.setTimestamp((Timestamp)sqlResult1.get(0).get(4));
		}
		return art;
	}

	public static boolean modifyArt(Art art) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql="update artContent set navId=?,catId=?,artId=?,title=?,image=?,content=?,timestamp=? where id=?";
		Object params[]={art.getNavId(),art.getCatId(),art.getArtId(),
						art.getTitle(),(art.getImage()==null?null:art.getImage().getId()),art.getContent(),
						new Timestamp(new Date().getTime()),art.getId()};
		return 1==sqlHelper.executeUpdate(sql, params);
	}

	public static Art createArt(Art art) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql1="select max(artId) from artContent where navId=? and catId=?";
		Object params1[]={art.getNavId(),art.getCatId()};
		ArrayList<ArrayList<Object>> sqlResult1=sqlHelper.executeQuery(sql1, params1,true,false);
		int maxArtId=0;
		if(sqlResult1.size()!=0 && sqlResult1.get(0).size()!=0 && sqlResult1.get(0).get(0)!=null) {
			maxArtId=(Integer)sqlResult1.get(0).get(0);
		}
		art.setArtId(maxArtId+1);
		
		String sql2="insert into artContent (navId,catId,artId,title,image,content,timestamp) values (?,?,?,?,?,?,?)";
		Object param2[]={art.getNavId(),art.getCatId(),art.getArtId(),
							art.getTitle(),(art.getImage()==null?null:art.getImage().getId()),art.getContent(),new Timestamp(new Date().getTime())};
		if(1!=sqlHelper.executeUpdate(sql2, param2,false,false)) {
			return null;
		}
		String sql3="select LAST_INSERT_ID()";
		ArrayList<ArrayList<Object>> sqlResult3=sqlHelper.executeQuery(sql3, null,false,true);
		art.setId(((BigInteger)sqlResult3.get(0).get(0)).intValue());
		
		return art;
	}

	public static boolean deleteArt(int navId, int catId, int artId) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql="delete from artContent where navId=? and catId=? and artId=?";
		Object[] params={navId,catId,artId};
		return sqlHelper.executeUpdate(sql, params)==1;
	}
}
