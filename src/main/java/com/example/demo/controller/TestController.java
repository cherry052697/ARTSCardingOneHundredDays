package com.example.demo.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.User;
import com.example.demo.domain.UserDeliveryAddress;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Json;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AddressRepository addressRepository;
	
	@RequestMapping(value = "/hi")
	public @ResponseBody String sayHi(String name){
		return "hi,my name is "+name;
	}
	
	@RequestMapping(value = "/getUserInfo")
	public @ResponseBody String getUserInfo(String name){
		List<User> user = userRepository.findByName(name);
		String result = Json.toJson(user);
		log.info(result);
		return result;
	}
	
	@RequestMapping(value = "/getAddressInfo")
	public @ResponseBody String getAddressInfo(String id){
		Optional<UserDeliveryAddress> address = addressRepository.findById(id);
		UserDeliveryAddress result = address.get();
		log.info(Json.toJson(result));
		return Json.toJson(result);
	}
	

}
