package com.team1.stayhealthy.controller;

import java.util.ArrayList;
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

	private static final String REQUEST_TYPE = "Request_type";
	private static final String Request_prop_owner_email = "owner_prop";
	private static final String Request_prop_requestor_email = "requestor_prop";
	private static final String Request_prop_approved = "approved_prop";
	private static final String Request_prop_requestor_name = "requestor_prop_name";
	private static final String Request_prop_owner_name = "owner_prop_name";

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody
	User getRecosInJSON(@PathVariable String name) {

		User user = new User();
		user.setId(name);
		user.setRecommendations(new String[] { "recommendation1", "recommendation2" });

		return user;

	}

	@RequestMapping(value = "/saveRequest", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody
	ServerResponseModel getPermission(@RequestBody RemoteRequestModel requestmodel) {
		ServerResponseModel srm;
		try {
			// Key requestKey = KeyFactory.createKey("Request",
			// requestmodel.getRequestorEmail());
			String keyValue = requestmodel.getKeyValue();
			Entity request = new Entity(REQUEST_TYPE, keyValue);
			request.setProperty(Request_prop_requestor_email, requestmodel.getRequestorEmail());
			request.setProperty(Request_prop_owner_email, requestmodel.getOwnerEmail());
			request.setProperty(Request_prop_approved, requestmodel.isApproved());
			request.setProperty(Request_prop_owner_name, requestmodel.getOwnerName());
			request.setProperty(Request_prop_requestor_name, requestmodel.getRequestorName());
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			datastore.put(request);
		} catch (Exception e) {
			srm = new ServerResponseModel();
			srm.setType(ResponseType.SEND_REQUEST);
			srm.setSuccessful(false);
			srm.setServerMessage(e.getMessage());
			return srm;
		}
		srm = new ServerResponseModel();
		srm.setType(ResponseType.SEND_REQUEST);
		srm.setSuccessful(true);
		return srm;

	}

	@RequestMapping(value = "/checkRequest", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody
	ServerResponseModel checkStatus(@RequestBody RemoteRequestModel requestmodel) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		ArrayList<Entity> requestEntity = new ArrayList<Entity>();
		ArrayList<RemoteRequestModel> results = new ArrayList<RemoteRequestModel>();
		System.out.println("hey");
		ServerResponseModel srm;
		try {
			Query query = new Query(REQUEST_TYPE);
			FilterPredicate fp = new FilterPredicate(Request_prop_owner_email, FilterOperator.EQUAL,
					requestmodel.getOwnerEmail());
			query.setFilter(fp);
			PreparedQuery pq = datastore.prepare(query);
			requestEntity = new ArrayList<Entity>(pq.asList(FetchOptions.Builder.withDefaults()));
			for (Entity request : requestEntity) {
				RemoteRequestModel resultRequest = new RemoteRequestModel();
				resultRequest.setOwnerEmail((String) request.getProperty(Request_prop_owner_email));
				resultRequest.setRequestorEmail((String) request.getProperty(Request_prop_requestor_email));
				resultRequest.setOwnerName((String) request.getProperty(Request_prop_owner_name));
				resultRequest.setRequestorName((String) request.getProperty(Request_prop_requestor_name));
				resultRequest.setApproved((boolean) request.getProperty(Request_prop_approved));
				results.add(resultRequest);
			}
			
		} catch (Exception e) {
			srm = new ServerResponseModel();
			srm.setType(ResponseType.CHECK_REQUEST);
			srm.setSuccessful(false);
			srm.setServerMessage(e.getMessage());
			return srm;
		}
		srm = new ServerResponseModel();
		srm.setType(ResponseType.CHECK_REQUEST);
		srm.setSuccessful(true);
		srm.setRequests(results);

		// HashMap<String,Object> hm = new HashMap<String,Object>();
		// hm.put("requests", requests);
		// hm.put("response", srm);
		// return hm;
		return srm;

	}

	@RequestMapping(value = "/approveRequest", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody
	ServerResponseModel approveRequest(@RequestBody RemoteRequestModel requestmodel) {
		ServerResponseModel srm;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		try {
			Query query = new Query(REQUEST_TYPE);
			Filter fp = new FilterPredicate(Request_prop_owner_email, FilterOperator.EQUAL,
					requestmodel.getOwnerEmail());
			Filter fp2 = new FilterPredicate(Request_prop_requestor_email, FilterOperator.EQUAL,
					requestmodel.getRequestorEmail());
			Filter matchFilter = CompositeFilterOperator.and(fp, fp2);

			query.setFilter(matchFilter);
			PreparedQuery pq = datastore.prepare(query);
			Entity requestEntity = pq.asSingleEntity();
			requestEntity.setProperty(Request_prop_approved, requestmodel.isApproved());
			datastore.put(requestEntity);
		} catch (Exception e) {
			srm = new ServerResponseModel();
			srm.setType(ResponseType.APPROVE_REQUEST);
			srm.setSuccessful(false);
		}
		srm = new ServerResponseModel();
		srm.setType(ResponseType.APPROVE_REQUEST);
		srm.setSuccessful(true);
		return srm;

	}

	@RequestMapping(value = "/checkRequestStatus", method = RequestMethod.POST, headers = { "content-type=application/json" })
	public @ResponseBody
	ServerResponseModel checkRequestStatus(@RequestBody RemoteRequestModel requestmodel) {
		ServerResponseModel srm;
		Entity requestEntity;
		Boolean status = null;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		try {
			Query query = new Query(REQUEST_TYPE);
			Filter fp = new FilterPredicate(Request_prop_owner_email, FilterOperator.EQUAL,
					requestmodel.getOwnerEmail());
			Filter fp2 = new FilterPredicate(Request_prop_requestor_email, FilterOperator.EQUAL,
					requestmodel.getRequestorEmail());
			Filter matchFilter = CompositeFilterOperator.and(fp, fp2);

			query.setFilter(matchFilter);
			PreparedQuery pq = datastore.prepare(query);
			requestEntity = pq.asSingleEntity();
			status = (Boolean) requestEntity.getProperty(Request_prop_approved);

		} catch (Exception e) {
			srm = new ServerResponseModel();
			srm.setType(ResponseType.CHECK_REQUEST_STATUS);
			srm.setSuccessful(false);
			srm.setServerMessage(e.getMessage());
		}
		srm = new ServerResponseModel();
		srm.setType(ResponseType.CHECK_REQUEST_STATUS);
		srm.setSuccessful(true);
		//to be implemented
		return srm;

	}


}
