package com.pages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class LibraryTest {
	public static void main(String[] args) throws IOException {
		/*
		 * String path = "src/test/resources/libraaryPayLoad.json"; String payLoad =
		 * generateStringFromResource(path);
		 * 
		 * String updates = String.format(payLoad,"abc2484392","BOG3882");
		 */
		//System.out.println(payLoad);
		//System.out.println(updates);
		
		
		addBookToLibrary();
		getBookByID();
	}

	
	
	public static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
	public static String id;
	
	
	public static void addBookToLibrary() throws IOException {
		String path = "src/test/resources/libraaryPayLoad.json";
		String payLoad = generateStringFromResource(path);
		String updates =  String.format(payLoad,"24yzz","1002");
		
		RestAssured.baseURI = "http://216.10.245.166/Library";
		String resposneBody = given()
		.header("Content-Type", "application/json")
		.body(updates)
		.log().all()
		.when()
		.post("/Addbook.php")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract().asString();
		
		
		JsonPath js = new JsonPath(resposneBody);
		 
		id = js.getString("ID");
		System.out.println(id);

	}
	
	public static void getBookByID() {
		
		
		//http://216.10.245.166/Library/GetBook.php?ID=
		RestAssured.baseURI = "http://216.10.245.166/Library";
		given()
		.header("Content-Type", "application/json")
		.log().all()
		.when()
		.get("/GetBook.php?ID="+id)
		.then().log().all()
		.assertThat()
		.statusCode(200);
		System.out.println(id);
	}
	
	
	
	
	
}
