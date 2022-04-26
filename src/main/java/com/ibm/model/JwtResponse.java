package com.ibm.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private UserInfoResponse userInfo;

	public JwtResponse(String jwttoken, UserInfoResponse userInfo) {
		this.jwttoken = jwttoken;
		this.userInfo = userInfo;
	}

	public UserInfoResponse getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoResponse userInfo) {
		this.userInfo = userInfo;
	}

	public String getToken() {
		return this.jwttoken;
	}

}