package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.filter.MyFilter;

@SpringBootApplication
@EnableConfigurationProperties
@RestController("/")
public class Demo2Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Demo2Application.class, args);
	}
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean  
	    public FilterRegistrationBean  filterRegistrationBean() {  
	        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
	        MyFilter httpBasicFilter = new MyFilter();  
	        registrationBean.setFilter(httpBasicFilter);  
	        List<String> urlPatterns = new ArrayList<String>();  
	        urlPatterns.add("/test/*");  
	        registrationBean.setUrlPatterns(urlPatterns);  
	        System.out.println("过滤URL");
	        return registrationBean;  
	    }  

}
