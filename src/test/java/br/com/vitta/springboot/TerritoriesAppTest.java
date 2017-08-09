package br.com.vitta.springboot;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class TerritoriesAppTest {
	
	public static final String REST_SERVICE_URI = "http://localhost:8080/TerritoriesApi/";
	
	  /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllTerritories(){
        System.out.println("Testing listAllTerritories API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> territoriesMap = restTemplate.getForObject(REST_SERVICE_URI+"/territories/", List.class);
         
        if(territoriesMap!=null){
            for(LinkedHashMap<String, Object> map : territoriesMap){
               
            }
        }else{
            System.out.println("No user exist----------");
        }
    }
    
    public static void main(String args[]){
    	
    	listAllTerritories();
    }
	
}
