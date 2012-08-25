package com.kurui.kums.mongodb.util;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDB {

	// 连接数据库
	public static DB getMongoDB(String host, String dbname) {

		Mongo m = null;
		try {
			m = new Mongo(host, 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		DB db = m.getDB(dbname);

		return db;
	}
}
