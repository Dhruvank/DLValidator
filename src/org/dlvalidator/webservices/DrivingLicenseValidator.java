package org.dlvalidator.webservices;

import threescale.v3.api.AuthorizeResponse;
import threescale.v3.api.ParameterMap;
import threescale.v3.api.ServerError;
import threescale.v3.api.ServiceApi;
import threescale.v3.api.impl.ServiceApiDriver;

public class DrivingLicenseValidator {


    public String validateDrivingLicense(String drivingLicense, String userApikey)  
    {  
    	validateRequest(userApikey);
    	
    	if(drivingLicense.length()<10) 
    		return "Invalid Driving License";
    	
        return "DrivingLicense: "+ drivingLicense;  
    } 
    
    public void validateRequest(String userApikey)  
    {  

  	  	String providerkey = "bb609ac6eb747f1aef145b10b5b08ad1";
  	  	String serviceId = "2555417724728";

    	  ServiceApi serviceApi = new ServiceApiDriver(providerkey); 

    	  ParameterMap params = new ParameterMap();      // the parameters of your call
    	  //String user_key = "c4895f9ef2d4eb170b39c360a1a8a3b1" "eed16ca5d16400d284a3756bc9aa9e4a";
    	  params.add("service_id", serviceId);  // Add the service id of your application
    	  params.add("user_key", userApikey);  // Add the user id of your application

    	  ParameterMap usage = new ParameterMap(); // Add a metric to the call
    	  usage.add("hits", "1");
    	  params.add("usage", usage);              // metrics belong inside the usage parameter

    	    AuthorizeResponse response = null;
    	    // the 'preferred way' of calling the backend: authrep
    	    try {
    	      response = serviceApi.authrep(params);
    	      System.out.println("AuthRep on User Key Success: " + response.success());
    	      if (response.success() == true) {
    	        // your api access got authorized and the  traffic added to 3scale backend
    	        System.out.println("Plan: " + response.getPlan());
    	      } else {
    	        // your api access did not authorized, check why
    	        System.out.println("Error: " + response.getErrorCode());
    	        System.out.println("Reason: " + response.getReason());
    	      }
    	    } catch (ServerError serverError) {
    	      serverError.printStackTrace();
    	    }
    	
    	
    } 
    
    

}
