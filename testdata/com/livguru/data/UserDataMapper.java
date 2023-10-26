package com.livguru.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import liveguru.user.GlobalConstants;

public class UserDataMapper {

	public static UserDataMapper getUserData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.getGlobalConstants().getProjectPath() + "/resource/UserData.json"), UserDataMapper.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("emailAddress")
	private String emailAddress;

	@JsonProperty("new_emailAddress")
	private String new_emailAddress;

	@JsonProperty("password")
	private String password;

	@JsonProperty("day")
	private String day;

	@JsonProperty("month")
	private String month;

	@JsonProperty("year")
	private String year;

	@JsonProperty("login")
	private Login login;

	@JsonProperty("country")
	private String country;

	@JsonProperty("city")
	private String city;

	@JsonProperty("new_city")
	private String new_city;

	@JsonProperty("address")
	private String address;

	@JsonProperty("new_address")
	private String new_address;

	@JsonProperty("zipcode")
	private String zipcode;

	@JsonProperty("new_zipcode")
	private String new_zipcode;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("new_phone")
	private String new_phone;

	@JsonProperty("state")
	private String state;

	static class Login {
		@JsonProperty("username")
		private String username;

		@JsonProperty("password")
		private String password;
	}

	public String getLoginUsername() {
		return login.username;
	}

	public String getLoginPassword() {
		return login.password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getNewEmailAddress() {
		return new_emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public String getDay() {
		return day;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getNewCity() {
		return new_city;
	}

	public String getAddress() {
		return address;
	}

	public String getNewAddress() {
		return new_address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getNewZipcode() {
		return new_zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public String getNewPhone() {
		return new_phone;
	}

	public String getState() {
		return state;
	}

}
