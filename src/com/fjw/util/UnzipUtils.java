package com.fjw.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
/**
 *将文件或是文件夹打包压缩成zip格式
 * @author ysc
 */
public class UnzipUtils {

    public UnzipUtils(){};

    /**
     * APDPlat中的重要打包机制
     * 将jar文件中的某个文件夹里面的内容复制到某个文件夹
     * @param jar 包含静态资源的jar包
     * @param subDir jar中包含待复制静态资源的文件夹名称
     * @param loc 静态资源复制到的目标文件夹
     * @param force 目标静态资源存在的时候是否强制覆盖
     */
    private String fromFilename;
    private String subDirname;
    private String toFilename;
    private boolean isForce;
    public void unZip() throws IOException,ZipException{
    	ZipFile zip=null;
        try {
            File base=new File(toFilename);
            if(!base.exists()){
                base.mkdirs();
            }

            zip=new ZipFile(new File(fromFilename));
            Enumeration<? extends ZipEntry> entrys = zip.entries();
            while(entrys.hasMoreElements()){
                ZipEntry entry = entrys.nextElement();
                String name=entry.getName();
                if(!name.startsWith(subDirname)){
                    continue;
                }
                //去掉subDir
                name=name.replaceFirst(subDirname,"").trim();
                if(name.length()<2){
                    System.out.println(name+" 长度 < 2");
                    continue;
                }
                if(entry.isDirectory()){
                    File dir=new File(base,name);
                    if(!dir.exists()){
                        dir.mkdirs();
                    }else{
                    }
                    System.out.println(name+" 是目录");
                }else{
                    File file=new File(base,name);
                    if(file.exists() && isForce){
                        file.delete();
                    }
                    if(!file.exists()){
                        InputStream in=zip.getInputStream(entry);
                        if(true==copyFile(in,file)){
                        	System.out.println("创建文件成功："+file.getAbsolutePath());
                        } else {
                        	System.out.println("创建文件失败："+file.getAbsolutePath());
                        }
                        
                    }else{
                    	System.out.println("文件已经存在");
                    }
                }
            }
        } finally {
        	try {
				if(zip!=null) zip.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
	private boolean copyFile(InputStream in, File file) {
		FileOutputStream fos=null;
		try{
			File path=file.getParentFile();
			if(path.isFile()) {
				System.out.println("需要创建文件夹， 但是存在相同文件");
				return false;
			} else if(path.exists()==false && path.mkdirs()==false) {
				System.out.println("创建文件夹失败");
				return false;
			}
			fos=new FileOutputStream(file);
			byte[] buf=new byte[4096];
			int num=0;
			while((num=in.read(buf))!=-1) {
				fos.write(buf, 0, num);
			}
			return true;
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(fos!=null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String getFromFilename() {
		return fromFilename;
	}
	public void setFromFilename(String fromFilename) {
		this.fromFilename = fromFilename;
	}
	public String getSubDirname() {
		return subDirname;
	}
	public void setSubDirname(String subDirname) {
		this.subDirname = subDirname;
	}
	public String getToFilename() {
		return toFilename;
	}
	public void setToFilename(String toFilename) {
		this.toFilename = toFilename;
	}
	public boolean isForce() {
		return isForce;
	}
	public void setForce(boolean isForce) {
		this.isForce = isForce;
	}
    

}