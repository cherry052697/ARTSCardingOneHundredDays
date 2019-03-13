package com.cherry.designpatterns.objectpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionPool extends ObjectPool<Connection> {

	private String dsn, usr, pwd;

	public JDBCConnectionPool(String driver, String dsn, String usr, String pwd) {
		super();
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dsn = dsn;
		this.usr = usr;
		this.pwd = pwd;
	}

	@Override
	protected Connection create() {
		try {
			return (DriverManager.getConnection(dsn, usr, pwd));
		} catch (SQLException e) {
			e.printStackTrace();
			return (null);
		}
	}

	@Override
	public void expire(Connection o) {
		try {
			((Connection) o).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean validate(Connection o) {
		try {
			return (!((Connection) o).isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
			return (false);
		}
	}
	public static void main(String args[]) {
	    // Do something...

	    // Create the ConnectionPool:
	    JDBCConnectionPool pool = new JDBCConnectionPool(
	      "com.mysql.jdbc.Driver", "jdbc:mysql://rm-2ze2hfxhg79696ev2to.mysql.rds.aliyuncs.com:3306/biran-dev?useUnicode=true&characterEncoding=UTF-8&useSSL=false",
	      "brzhongyi", "Root1qaz2wsx3edc");

	    // Get a connection:
	    Connection con = pool.checkOut();

	    // Use the connection

	    // Return the connection:
	    pool.checkIn(con);
	 
	  }
}