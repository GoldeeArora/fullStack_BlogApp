package com.blogApplication.blogDemo.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.jsonwebtoken.io.IOException;

@Service
public class ImageService {
@Value("${application.bucket.name}")
private String bucketName;
@Autowired
private AmazonS3 s3Client;
public String uploadFileToS3Bucket(MultipartFile file) throws IOException
{
	String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	File fileObj = convertMultipartFileToFile(file);
	try {
		PutObjectRequest putRequest = new PutObjectRequest(bucketName, fileName, fileObj);
        s3Client.putObject(putRequest);
        fileObj.delete();
	}
	catch(AmazonServiceException e)
	{
		e.printStackTrace();
	}
	catch(SdkClientException e)
	{
		e.printStackTrace();
	}
	return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
}
private File convertMultipartFileToFile(MultipartFile file)
{
	
	File convertedFile = new File(file.getOriginalFilename());
	try(FileOutputStream fos =  new FileOutputStream(convertedFile))
	{
		fos.write(file.getBytes());
	}
	catch(Exception e){
		System.out.println("Error converting the multiparfile to file");
	}
	return convertedFile;
}
}
