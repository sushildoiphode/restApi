package com.pages;

import io.restassured.path.json.JsonPath;

public class JsonReadingDemo {
	public static void main(String[] args) {
		
		String s = "{\"dashboard\":{\"purchaseAmount\":\"38000\",\"website\":\"testingshastra.com\"},\"courses\":[{\"title\":\"Selenium\",\"price\":9000},{\"title\":\"Python Selenium\",\"price\":1000},{\"title\":\"Headless Testing \",\"price\":4000},{\"title\":\"Rest API\",\"price\":6000},{\"title\":\"Postman\",\"price\":2000},{\"title\":\"Postman\",\"price\":1000},{\"title\":\"C-Test\",\"price\":8000}]}";
		
		JsonPath path = new JsonPath(s);
		String newValue  = path.getString("courses[0].title");
		System.out.println(newValue);
		
		int courseSize = path.getInt("courses.size()");
		//System.out.println(path.getMap("courses").toString());
		System.out.println(courseSize);
		
		
		  for(int i=0; i<courseSize;i++) {
		 	
			  String titles = path.getString("courses[" + i + "].title");
			  System.out.println(titles);
			  
			  String prices = path.getString("courses[" + i + "].price");
			  System.out.println(prices);
			 
		  }
		 
	}
}
