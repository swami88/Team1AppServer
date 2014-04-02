package com.team1.stayhealthy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team1.stayhealthy.model.RemoteRequestModel;
import com.team1.stayhealthy.model.ServerResponseModel;
import com.team1.stayhealthy.model.ServerResponseModel.ResponseType;
import com.team1.stayhealthy.model.User;

 
@Controller
@RequestMapping("/getReco/*")
public class MainController {
 
	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody User getRecosInJSON(@PathVariable String name) {
 
		User user = new User();
		user.setId(name);
		user.setRecommendations(new String[]{"recommendation1", "recommendation2"});
 
		return user;
 
	}
	
	@RequestMapping(value="getPermission", method = RequestMethod.POST,headers = {"content-type=application/json"})
	public @ResponseBody ServerResponseModel getPermission(@RequestBody RemoteRequestModel model){
		ServerResponseModel srm = new ServerResponseModel();
		srm.setType(ResponseType.CHECK_REQUEST);
		srm.setSuccessful(true);
		return srm;
		
	}
 
}

