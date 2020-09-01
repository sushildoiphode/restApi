package com.pages;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

public class DemoClass {
	public static void main(String[] args) {
		System.out.println("Demo class");
		//getMethod();
		//createEmployee();
		getBookByAuthorName();
		//deleteSpecificEmployee();
		
	}
	public static void getMethod() {
    // RestAssured.baseURI = "http://216.10.245.166";
		
		
		
		RestAssured.baseURI = "http://216.10.245.166";

        given()
                .body("{\n" +
                        "    \"name\": \"Bio of Ganesha\",\n" +
                        "    \"isbn\": \"Saurabh\",\n" +
                        "    \"aisle\": \"Deole123LOP2\",\n" +
                        "    \"author\": \"Shankar\"\n" +
                        "}\n")
                .header("Content-Type","application/json")
                .log().all()
                .when()
                .post("/Library/Addbook.php")
                .then().log().all();

	}
	
	public static void createEmployee() {
		RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1";
		given().
		body("{\r\n" + 
				"    \"name\": \"Kaustubh\",\r\n" + 
				"    \"salary\": \"36000\",\r\n" + 
				"    \"age\": \"25\"\r\n" + 
				"}").
		header("Content-Type","application/json").log().all().
		when().post("/create").
		then().log().all();
	}
	
	//query parameter code
	public static  void getBookByAuthorName() {
		HashMap map = new HashMap<String,String>();
		map.put("AuthorName", "Amish Tripathi");
		map.put("username","kaustubh");
		RestAssured.baseURI="http://216.10.245.166";
		given().
		header("Content-Type","application/json").log().all().
		queryParams(map).
		when().get("/Library/GetBook.php/").
		then().log().all();

	}
	
	//path parameter code
	public static void deleteSpecificEmployee() {
		String id = "8803";
		RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1";
		given().
		pathParam("id", id).
		header("Content-Type","application/json").log().all().
		when().delete("/delete/{id}").
		then().log().all();
		
		
	}
	
	
}
