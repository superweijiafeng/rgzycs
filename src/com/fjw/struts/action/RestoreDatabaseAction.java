/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.fjw.struts.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fjw.struts.form.RestoreDatabaseForm;
import com.fjw.dao.SQLHelper;
import com.fjw.databaseStructure.*;
/** 
 * MyEclipse Struts
 * Creation date: 04-16-2016
 * 
 * XDoclet definition:
 * @struts.action
 */
public class RestoreDatabaseAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RestoreDatabaseForm restoreDatabaseForm=(RestoreDatabaseForm)form;
		if(request.getSession().getAttribute("user")==null) {
			return mapping.findForward("goHome");
		}
		if(restoreDatabaseForm.getFile().getFileName().length()==0) {
			try {
				response.getWriter().println("请选择文件");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		PrintWriter out=null;
		InputStream is=null;
		OutputStream os=null;
		String tmpDir="tmp"+(new Random().nextInt(1000000));
		String tmpFile=new Random().nextInt(1000000)+"";
		String realDir=this.getServlet().getServletContext().getRealPath(tmpDir);
		String realFile=this.getServlet().getServletContext().getRealPath(tmpDir+"/"+tmpFile);
		try {
			out=response.getWriter();
			File fileTmpDir=new File(realDir);
			out.println("创建临时文件夹<br/>");
			fileTmpDir.mkdirs();
			out.println("上传文件到临时文件夹<br/>");
			is=restoreDatabaseForm.getFile().getInputStream();
			os=new FileOutputStream(realFile);
			byte[] buf=new byte[4096];
			int num=0;
			int count=0;
			while((num=is.read(buf))!=-1) {
				
				os.write(buf, 0, num);
				count+=num;
				if(count%(1024*1024)==0) {
					out.println("已上传："+count+"/"+restoreDatabaseForm.getFile().getFileSize()+"<br/>");
				}
			}
			out.println("上传成功<br/>");
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if(os!=null) os.close();
				if(is!=null) is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		ObjectInputStream ois=null;
		ArrayList<Admin> admin=null;
		ArrayList<NavContent> navs=null;
		ArrayList<CatContent> cats=null;
		ArrayList<ArtContent> arts=null;
		ArrayList<FileContent> files=null;
		try{
			ois=new ObjectInputStream(new FileInputStream(realFile));
			out.println("读取数据文件<br/>");
			admin=(ArrayList<Admin>) ois.readObject();
			navs=(ArrayList<NavContent>) ois.readObject();
			cats=(ArrayList<CatContent>) ois.readObject();
			arts=(ArrayList<ArtContent>) ois.readObject();
			files=(ArrayList<FileContent>) ois.readObject();
			out.println("读取数据文件成功<br/>");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ois!=null) ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		out.println("删除数据库原先内容<br/>");
		SQLHelper sqlHelper=new SQLHelper();

		ArrayList<ArrayList<Object>> fileId=sqlHelper.executeQuery("select id from fileContent", null);
		for(int i=0;i<fileId.size();i++) {
			Integer id=(Integer) fileId.get(i).get(0);
			sqlHelper.executeUpdate("delete from fileContent where id=?", new Object[]{id});
		}
		ArrayList<ArrayList<Object>> artId=sqlHelper.executeQuery("select id from artContent", null);
		for(int i=0;i<artId.size();i++) {
			Integer id=(Integer) artId.get(i).get(0);
			sqlHelper.executeUpdate("delete from artContent where id=?", new Object[]{id});
		}
		ArrayList<ArrayList<Object>> catId=sqlHelper.executeQuery("select id from catContent", null);
		for(int i=0;i<catId.size();i++) {
			Integer id=(Integer) catId.get(i).get(0);
			sqlHelper.executeUpdate("delete from catContent where id=?", new Object[]{id});
		}
		ArrayList<ArrayList<Object>> navId=sqlHelper.executeQuery("select navId from navContent", null);
		for(int i=0;i<navId.size();i++) {
			Integer id=(Integer) navId.get(i).get(0);
			sqlHelper.executeUpdate("delete from navContent where navId=?", new Object[]{id});
		}
		ArrayList<ArrayList<Object>> adminId=sqlHelper.executeQuery("select username from admin", null);
		for(int i=0;i<adminId.size();i++) {
			String username=(String) adminId.get(i).get(0);
			sqlHelper.executeUpdate("delete from admin where username=?", new Object[]{username});
		}

		
		out.println("恢复数据<br/>");
		for(int i=0;i<admin.size();i++) {
			
			String sql="insert into admin values (?,?)";
			Object[] params={admin.get(i).getUsername(),admin.get(i).getPassword()};
			if(sqlHelper.executeUpdate(sql, params)!=1) {
				out.println("恢复失败<br/>");
				return null;
			}
		}
		for(int i=0;i<navs.size();i++) {
			NavContent nav=navs.get(i);
			
			
			String sql="insert into navContent values (?,?,?,?,?)";
			Object[] params={nav.getNavId(),nav.getTitle(),nav.getImage(),nav.getContent(),nav.getTimestamp()};
			if(sqlHelper.executeUpdate(sql, params)!=1) {
				out.println("恢复失败<br/>");
				return null;
			}
		}
		for(int i=0;i<cats.size();i++) {
			CatContent cat=cats.get(i);
			
			String sql="insert into catContent values (?,?,?,?,?,?,?)";
			Object[] params={cat.getId(),cat.getNavId(),cat.getCatId(),cat.getTitle(),cat.getImage(),cat.getContent(),cat.getTimestamp()};
			if(sqlHelper.executeUpdate(sql, params)!=1) {
				out.println("恢复失败<br/>");
				return null;
			}
		}
		for(int i=0;i<arts.size();i++){
			ArtContent art=arts.get(i);
			
			String sql="insert into artContent values (?,?,?,?,?,?,?,?)";
			Object[] params={art.getId(),art.getNavId(),art.getCatId(),art.getArtId(),art.getTitle(),art.getImage(),art.getContent(),art.getTimestamp()};
			if(sqlHelper.executeUpdate(sql, params)!=1) {
				out.println("恢复失败<br/>");
				return null;
			}
		}
		for(int i=0;i<files.size();i++) {
			FileContent file=files.get(i);
						
			String sql="insert into fileContent values (?,?,?)";
			Object[] params={file.getId(),file.getFilename(),file.getShowFilename()};
			if(sqlHelper.executeUpdate(sql, params)!=1) {
				out.println("恢复失败<br/>");
				return null;
			}
		}
		out.println("恢复成功<br/>");
		
		out.println("删除临时文件和临时文件夹<br/>");
		File fileRealFile=new File(realFile);
		if(fileRealFile.delete()==true){
			out.println("删除临时文件成功<br/>");
		} else {
			out.println("删除临时文件失败<br/>");
		}
		File fileRealDir=new File(realDir);
		if(fileRealDir.delete()==true){
			out.println("删除临时文件夹成功<br/>");
		} else {
			out.println("删除临时文件夹失败<br/>");
		}
		out.close();
		return null;
	}
}