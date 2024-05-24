package com.prayansh.blog.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prayansh.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		String name=file.getOriginalFilename();

		String randomID=UUID.randomUUID().toString();
		String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));

		String filePath =path+File.separator+fileName1;
		//create folder
		File f=new File(path);
		if(!f.exists())
		{
			f.mkdir();
		}
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName1;
//		return null;
	}

	@Override
	public InputStream getSource(String path, String filename) throws FileNotFoundException {
		String fullPath=path+File.separator+filename;
		InputStream is =new FileInputStream(fullPath);
		return is;
	}


}
