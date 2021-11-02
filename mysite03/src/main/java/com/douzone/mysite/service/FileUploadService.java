package com.douzone.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.FileUploadException;

@Service
public class FileUploadService {
	private static String SAVE_PATH = "/upload-mysite";  // c 드라이브 저장
	private static String URL_BASE = "/upload/images";	 // 가상 url
	
	public String restoreImage(MultipartFile file) throws FileUploadException {
		try {
			File uploadDirectory = new File(SAVE_PATH);
			if(!uploadDirectory.exists()) {  
				uploadDirectory.mkdir();   // 만약에 폴더가 없으면 폴더 만들어라
			}
			
			if(file.isEmpty()) {
				throw new FileUploadException("file upload error: image empty");
			}
			
			String originFilename = file.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.')+1);
			String saveFilename = generateSaveFilename(extName);
			
			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename); // 실제 내c드라이브에 저장되는 경로
			os.write(data);
			os.close();

			return URL_BASE + "/" + saveFilename;
			
		} catch(IOException ex) {
			throw new FileUploadException("file upload error:" + ex);
		}
	}
	
	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}	
}