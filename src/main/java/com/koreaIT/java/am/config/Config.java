package com.koreaIT.java.am.config;

public class Config {
	public static String getDBUrl() {
		return "jdbc:mysql://localhost:3306/jsp_article_manager";
	}
	public static String getDBUser() {
		return "root";
	}
	public static String getDBPwd() {
		return "";
	}
	public static String getDBDriverName() {
		return "com.mysql.cj.jdbc.Driver";
	}
}
