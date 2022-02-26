package com.grv.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;

public class ReadlogFiles {

	public static void main(String[] args) throws Exception {
		
		String filename = "src/jsonfile/readfile.json";
        List<LogFile> logfiles = parseJSONFile(filename);
        int diff;
       for(int i=0;i<logfiles.size();i++) {
    	   diff=0;
    	   for(int j=1;j<logfiles.size();j++) {
    		   if(logfiles.get(i).getId().equals(logfiles.get(j).getId())){
    			   if(!(logfiles.get(i).getState().equals(logfiles.get(j).getState()))) {
    				   if(logfiles.get(i).getState().toUpperCase().equals("STARTED")) 
    					   diff=(int) (logfiles.get(j).getTimestamp() - logfiles.get(i).getTimestamp());   
    				   else 
    					   diff=(int) (logfiles.get(i).getTimestamp() - logfiles.get(j).getTimestamp());
    			   }
    		}
    		   if(diff>4) {
          		 	logfiles.get(i).setAlertFlag("true");
          		 	logfiles.get(i).setProcessedTime(diff);
          		}else {
          			logfiles.get(i).setAlertFlag("false");
           		   	logfiles.get(i).setProcessedTime(diff);
          		}
          	   }
       }
       LogEventsToHSQLDB.WritetoDb(logfiles);
       }
      
       
		
	
	public static List<LogFile> parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        Gson gson = new GsonBuilder().create();
        JsonStreamParser p = new JsonStreamParser(content);
        List<LogFile> lst=new ArrayList<LogFile>();
        while(p.hasNext()) {
        	JsonElement e=p.next();
        	if(e.isJsonObject()) {
        	 LogFile log= gson.fromJson(e, LogFile.class);
        	 lst.add(log);
        	}
        }
       return lst;
    }
	}

