package com.fjw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	private List<String> fileList;
	private String inputFile;
	private OutputStream outputStream;

	public ZipUtils() {
		fileList = new ArrayList<String>();
	}


	public void zipIt() throws IOException {
		String sourceFolder=inputFile;
		
		byte[] buffer = new byte[1024];
		String source = "";
		ZipOutputStream zos = null;
		try {
				source = sourceFolder.substring(
						sourceFolder.lastIndexOf(File.separator) + 1,
						sourceFolder.length());
			zos = new ZipOutputStream(outputStream);

			FileInputStream in = null;

			for (String file : this.fileList) {
				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try {
					in = new FileInputStream(sourceFolder + File.separator
							+ file);
					int len;
					while ((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				} finally {
					if(in!=null) in.close();
				}
			}

			zos.closeEntry();
			System.out.println("Folder successfully compressed");

		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void generateFileList() {
		this.fileList.clear();
		this.generateFileList(new File(inputFile));
	}
	
	private void generateFileList(File node) {

		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.toString()));

		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename));
			}
		}
	}

	private String generateZipEntry(String file) {
		return file.substring(inputFile.length() + 1, file.length());
	}

	public String getInputFile() {
		return inputFile;
	}


	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}


	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}


	public List<String> getFileList() {
		return fileList;
	}
}