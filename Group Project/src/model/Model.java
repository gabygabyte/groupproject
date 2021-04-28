package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Model 
{
	private static HashMap<String, String> h = new HashMap<String, String>();
	private static Properties properties = new Properties();//Create properties file to store hash map
	
	public static String getUserInfo(String compKey) throws IOException
	{
		String info;
        File file = new File("bookings.properties"); //opens file
        FileInputStream reader=new FileInputStream(file); //open reader
        properties.load(reader); //loads / lets you see stored values
        reader.close();

        for(String key: properties.stringPropertyNames()){//adding info to hash map
        	h.put(key, properties.get(key).toString());
        }
    	 if(h.containsKey(compKey)){ //check if there's a key in the hash map that matches 
 	       	info = h.get(compKey); //set info that goes with the person
         }
    	 else {
    		info = "Invalid email"; //The email doesn't go with any of the hotel info
    	 }
    	 
    	 return info;
	}
}
