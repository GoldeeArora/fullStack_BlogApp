package com.blogApplication.blogDemo.config;

import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {
	
//	 This is the method to call for the values that are stored inside of the application.properties and implement with the components 
	@Value("${cloud.aws.credential.accessKeyId}")
 private String accessKey;
	

	@Value("${cloud.aws.credential.secretKey}")
 private String secretKey;
	@Value("${cloud.aws.region.static}")
 private String region;
 
	
	//We are creating bean with name AmazonS3 which will be used in different components using @Autowired annotation
 @Bean
 public AmazonS3 generateS3Client()
 {
//	 This is used to create a credentials object for Aws which will help us to communicate with the aws service
           AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
           //Here we are returning an object and implementing methods to pass credentials,region and .build() for building the object 
           return AmazonS3ClientBuilder
        		   .standard()
        		   .withCredentials(new AWSStaticCredentialsProvider(credentials))
        		   .withRegion(region)
        		   .build();
 }
}
