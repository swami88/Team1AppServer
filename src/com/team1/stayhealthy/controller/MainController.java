package com.team1.stayhealthy.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.team1.stayhealthy.model.RemoteRequestModel;
import com.team1.stayhealthy.model.ServerResponseModel;
import com.team1.stayhealthy.model.ServerResponseModel.ResponseType;
import com.team1.stayhealthy.model.User;

 
@Controller
@RequestMapping("/getReco")
public class MainController {
 
	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody User getRecosInJSON(@PathVariable String name) {
 
		User user = new User();
		user.setId(name);
		user.setRecommendations(new String[]{"recommendation1", "recommendation2"});
 
		return user;
 
	}
	
	@RequestMapping(value="/saveRequest", method = RequestMethod.POST,headers = {"content-type=application/json"})
	public @ResponseBody ServerResponseModel getPermission(@RequestBody RemoteRequestModel requestmodel){
		ServerResponseModel srm;
		try
		{
		Key requestKey = KeyFactory.createKey("Request", requestmodel.getRequestorEmail());
		
		Entity request = new Entity("Request",requestKey);
		request.setProperty("requester", requestmodel.getRequestorEmail());
		request.setProperty("owner",requestmodel.getOwnerEmail());
		request.setProperty("status",requestmodel.isApproved());
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(request);
		}catch(Exception e){
			srm = new ServerResponseModel();
			srm.setType(ResponseType.SEND_REQUEST);
			srm.setSuccessful(false);
		}
		srm = new ServerResponseModel();
		srm.setType(ResponseType.SEND_REQUEST);
		srm.setSuccessful(true);
		return srm;
		
	}
	
	@RequestMapping(value="/checkRequest", method = RequestMethod.POST,headers = {"content-type=application/json"})
	public @ResponseBody HashMap<String,Object> checkStatus(@RequestBody RemoteRequestModel requestmodel){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		ArrayList<Entity> requests = new ArrayList<Entity>();
		ServerResponseModel srm;
		try
		{
			Query query = new Query("Request");
			FilterPredicate fp = new FilterPredicate("owner", FilterOperator.EQUAL, requestmodel.getOwnerEmail());
			query.setFilter(fp);
			PreparedQuery pq = datastore.prepare(query);
			 requests =(ArrayList<Entity>) pq.asList(FetchOptions.Builder.withDefaults());
		}catch(Exception e){
			srm = new ServerResponseModel();
			srm.setType(ResponseType.CHECK_REQUEST);
			srm.setSuccessful(false);
		}
		srm = new ServerResponseModel();
		srm.setType(ResponseType.CHECK_REQUEST);
		srm.setSuccessful(true);
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("requests", requests);
		hm.put("response", srm);
		return hm;
		
	}
	
	@RequestMapping(value="/approveRequest", method = RequestMethod.POST,headers = {"content-type=application/json"})
	public @ResponseBody ServerResponseModel approveRequest(@RequestBody RemoteRequestModel requestmodel){
		ServerResponseModel srm;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		try
		{
			Query query = new Query("Request");
			Filter fp = new FilterPredicate("owner", FilterOperator.EQUAL, requestmodel.getOwnerEmail());
			Filter fp2 = new FilterPredicate("requester", FilterOperator.EQUAL, requestmodel.getRequestorEmail());
			Filter matchFilter = CompositeFilterOperator.and(fp, fp2);

			 query.setFilter(matchFilter);
			 PreparedQuery pq = datastore.prepare(query);
			 Entity requestEntity = pq.asSingleEntity();
			 requestEntity.setProperty("status", requestmodel.isApproved());
			 datastore.put(requestEntity);
		}catch(Exception e){
			srm = new ServerResponseModel();
			srm.setType(ResponseType.APPROVE_REQUEST);
			srm.setSuccessful(false);
		}
		srm = new ServerResponseModel();
		srm.setType(ResponseType.APPROVE_REQUEST);
		srm.setSuccessful(true);
		return srm;
		
	}
	
	@RequestMapping(value="/checkRequestStatus", method = RequestMethod.POST,headers = {"content-type=application/json"})
	public @ResponseBody HashMap<String,Object> checkRequestStatus(@RequestBody RemoteRequestModel requestmodel){
		ServerResponseModel srm;
		 Entity requestEntity;
		 Boolean status = null;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		try
		{
			Query query = new Query("Request");
			Filter fp = new FilterPredicate("owner", FilterOperator.EQUAL, requestmodel.getOwnerEmail());
			Filter fp2 = new FilterPredicate("requester", FilterOperator.EQUAL, requestmodel.getRequestorEmail());
			Filter matchFilter = CompositeFilterOperator.and(fp, fp2);

			 query.setFilter(matchFilter);
			 PreparedQuery pq = datastore.prepare(query);
			 requestEntity = pq.asSingleEntity();
			status = (Boolean) requestEntity.getProperty("status");
			 
		}catch(Exception e){
			srm = new ServerResponseModel();
			srm.setType(ResponseType.CHECK_REQUEST_STATUS);
			srm.setSuccessful(false);
		}
		srm = new ServerResponseModel();
		srm.setType(ResponseType.CHECK_REQUEST_STATUS);
		srm.setSuccessful(true);
		
		HashMap<String,Object> hm = new HashMap<String,Object>();
		hm.put("status",status);
		hm.put("response",srm);
		return hm;
		
	}
 
}

