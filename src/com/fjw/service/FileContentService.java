package com.fjw.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.apache.struts.action.Action;
import org.apache.struts.upload.FormFile;
import com.fjw.dao.SQLHelper;
import com.fjw.domain.FileContent;

public class FileContentService {
	
	static HashMap<String, String> extMap = new HashMap<String, String>();
	static {
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,7z,rar,gz,bz2");
	}
	
	
	public static FileContent getFileContent(Integer id) {
		if(id==null) {
			return null;
		}
		
		FileContent fileContent=null;
		
		SQLHelper sqlHelper=new SQLHelper();
		String sql1="select filename,showFilename from fileContent where id=?";
		Object[] params1={id};
		ArrayList<ArrayList<Object>> sqlResult1=sqlHelper.executeQuery(sql1, params1);
		if(sqlResult1.size()!=0) {
			fileContent=new FileContent();
			fileContent.setId(id);
			fileContent.setFilename(sqlResult1.get(0).get(0).toString());
			fileContent.setShowFilename(sqlResult1.get(0).get(1).toString());
		}
		
		return fileContent;
	}
	
	public static FileContent createFileContent(FormFile formFile,Action action) {
		if(formFile==null || action==null) return null;
		//create directory
		Date date=new Date();
		String dirDate=new SimpleDateFormat("yyyyMMdd").format(date);
		String dirType="file";
		String ext="";
		int iLastIndexOfDot=formFile.getFileName().lastIndexOf(".");
		if(iLastIndexOfDot!=-1) {
			ext=formFile.getFileName().substring(iLastIndexOfDot+1);
			Iterator<String> it=extMap.keySet().iterator();
			while(it.hasNext()) {
				String key=it.next();
				if(extMap.get(key).indexOf(ext)!=-1) {
					dirType=key;
					break;
				}
			}
		}
		
		String relativePath="file/"+dirType+"/"+dirDate;
		File dir=new File(action.getServlet().getServletContext().getRealPath(relativePath));
		if(dir.isFile()==true) {
			//since there exist a file, cannot create a same directory
			return null;
		} else if(dir.exists()==false && dir.mkdirs()==false) {
			//cannot make directory for some reason
			return null;
		}

		//get random filename
		String relativeFilePath=null;
		File file=null;
		InputStream is=null;
		OutputStream os=null;
		do {
			relativeFilePath=relativePath+"/"+UUID.randomUUID().toString()+(ext.length()==0?"":"."+ext);
			file=new File(action.getServlet().getServletContext().getRealPath(relativeFilePath));
		} while(file.exists()==true);
		//write to file
		try {
			is=formFile.getInputStream();
			os=new FileOutputStream(file);
			byte[] buf=new byte[4096];
			int num=0;
			while((num=is.read(buf))!=-1) {
				os.write(buf, 0, num);
			}
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		} finally {
			try {
				if(is!=null) is.close();
				if(os!=null) os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//write filecontent to database
		FileContent fileContent=new FileContent();
		fileContent.setFilename(relativeFilePath);
		fileContent.setShowFilename(formFile.getFileName());
		fileContent=FileContentService.write(fileContent);
		
		return fileContent;
	}

	private static FileContent write(FileContent fileContent) {
		SQLHelper sqlHelper=new SQLHelper();
		String sql1="insert into fileContent (filename,showFilename) values (?,?)";
		Object[] params1={fileContent.getFilename(),fileContent.getShowFilename()};
		if(sqlHelper.executeUpdate(sql1, params1,true,false)!=1){
			return null;
		}
		String sql2="select LAST_INSERT_ID()";
		ArrayList<ArrayList<Object>> sqlResult2=sqlHelper.executeQuery(sql2, null,false,true);
		fileContent.setId(((BigInteger)sqlResult2.get(0).get(0)).intValue());
		return fileContent;
	}
}
