package com.ibm.config;

public interface IConstInterface {
	public static final String SECRET = "jwt_Authentication";
	public static final long EXPIRATION_TIME = 864000000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORIZATION = "Authorization";
	public static final String SIGN_UP_URL = "/user";
	public static final String UNDERSCORE = "_";

}
