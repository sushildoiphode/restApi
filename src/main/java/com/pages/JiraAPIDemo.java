package com.pages;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

public class JiraAPIDemo {
	
	public static final String SESSION_URL = "rest/auth/1/session";
	
	public static final String CREATE_TICKET = "rest/api/2/issue";
	
	
	public static void createTicket() {
		
		SessionFilter filter = new SessionFilter();
		System.out.println(filter.toString());
		//creating session code..
		
		RestAssured.baseURI="http://localhost:8080/";
		given()
		.contentType(ContentType.JSON)
		.body(PayloadDemo.createSessionpayload) // fetching string payload body from PayloadDemo class
		.log().all()
		.filter(filter)
		.when()
		.post(SESSION_URL)
		.then()
		.assertThat()
		.statusCode(200)
		.body("session.value", Matchers.notNullValue())
		.log().all();
		
		System.out.println(filter.toString());
		//creating a new ticket code:
		
		given()
		.contentType(ContentType.JSON)
		.body(PayloadDemo.createTicketPayload) //fetching string payload body from PayloadDemo class
		.log().all()
		.filter(filter)
		.when()
		.post(CREATE_TICKET)
		.then()
		.assertThat()
		.statusCode(201)
		.log().all();
		
		
	}
	
	
	public static void main(String[] args) {
		JiraAPIDemo.createTicket();
	}
}
