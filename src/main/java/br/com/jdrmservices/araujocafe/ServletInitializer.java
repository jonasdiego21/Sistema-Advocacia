package br.com.jdrmservices.araujocafe;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AraujocafeApplication.class);
	}
	
	 @Bean
	 public MultipartConfigElement multipartConfigElement() {
	     return new MultipartConfigElement("");
	 }

	 @Bean
	 public MultipartResolver multipartResolver() {
	     CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	     multipartResolver.setMaxUploadSize(1000000);
	     
	     return multipartResolver;
	 }

}
