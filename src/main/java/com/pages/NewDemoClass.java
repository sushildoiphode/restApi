package com.pages;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NewDemoClass {
	public static void main(String[] args) throws IOException {
		createNewUser();
		//getPosts();
		
		
		  String path = "src\\test\\resources\\userPayload.json"; String payLoad =
		  generateStringFromResource(path);
		  
		  String updatedPayLoad = String.format(payLoad,
		  "Tony96","Hanks","tony2788@gmail.com"); System.out.println(payLoad);
		  System.out.println(updatedPayLoad);
		 
		

	}
	
	 public static String generateStringFromResource(String path) throws IOException {
	        return new String(Files.readAllBytes(Paths.get(path)));
	    }

	public static void createNewUser() throws IOException {
		String path = "src\\test\\resources\\userPayload.json";
		String payLoad = generateStringFromResource(path);
		RestAssured.baseURI="https://gorest.co.in/public-api";
		given()
		.relaxedHTTPSValidation()
		.body(payLoad)
		.header("Authorization","Bearer QHg-NmZ6J2N6ipPyo_e0csmZkBDimy567wdX")
		.header("Content-Type","application/json")
		.log().all()
		.when().post("/users")
		.then().assertThat().statusCode(200).log().all();
	
	}
	
	
	
	public static void getPosts() {
		
		
		RestAssured.baseURI="https://gorest.co.in/public-api";
		String body = given()
		.relaxedHTTPSValidation()
		.header("Authorization", "Bearer KxH5L7KnBoBeWPmtpuvoNpWPgRamDNNMEEYy")
		.header("Content-Type","application/json")
		.log().all()
		.when().get("/posts")
		.then()
		.assertThat()
		.statusCode(200)
		.log().all()
		.extract().body().asString();
		
		JsonPath js = new JsonPath(body);
		 
		int idCount = js.getInt("result.size()");
		
		System.out.println(idCount);
		
		for(int i=0; i<idCount;i++)
		{
			
			  String prices = js.getString("result[" + i + "].id");
			  System.out.println(prices);
		}

	}

}
