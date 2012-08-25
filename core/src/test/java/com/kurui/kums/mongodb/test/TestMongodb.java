package com.kurui.kums.mongodb.test;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Set;

import junit.framework.TestCase;

import com.kurui.kums.mongodb.util.MongoDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class TestMongodb extends TestCase {

	public static void testQueryCollectionNames() {
//		DB db = MongoDB.getMongoDB("localhost", "test");
		DB db = MongoDB.getMongoDB("192.168.150.1", "test");
		
		// 遍历 所有的集合
		Set<String> colls = db.getCollectionNames();
		for (String s : colls) {
			System.out.println("collection name:" + s);
		}
	}

	public static void testInsertData() {
		DB db = MongoDB.getMongoDB("localhost", "test");
		// 插入数据
		DBCollection coll = db.getCollection("collection1");
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", "lijm");
		doc.put("age", 28);
		doc.put("time", new Date());
		coll.insert(doc);

		doc.put("name", "lijm001");
		doc.put("age", 29);
		doc.put("time", new Date());
		coll.insert(doc);

		// insert函数也支持插入文档列表：
		// insert(List<DBObject> list)

	}

	public static void testFindData() {
		DB db = MongoDB.getMongoDB("localhost", "test");
		DBCollection coll = db.getCollection("collection1");
		
		// 检索数据
		BasicDBObject cond = new BasicDBObject();
		cond.put("name", "lijm2");
		DBObject ret = coll.findOne(cond);
		System.out.println(ret);

		
//		Grid
	
	}


	public static void testFindAllData() {
		DB db = MongoDB.getMongoDB("localhost", "test");
		DBCollection coll = db.getCollection("collection1");
		
		// 检索所有数据
		DBCursor rct = coll.find();
		while (rct.hasNext()) {
			System.out.println(rct.next());
		}
	}
	
	/**
	 * @param args
	 * @throws MongoException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException,
			MongoException {

		// 连接数据库
		Mongo m = new Mongo("localhost", 27017);
		DB db = m.getDB("test");

		// 遍历 所有的集合
		Set<String> colls = db.getCollectionNames();
		for (String s : colls) {
			System.out.println("collection name:" + s);
		}

		// 插入数据
		DBCollection coll = db.getCollection("collection1");
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", "lijm");
		doc.put("age", 28);
		doc.put("time", new Date());
		coll.insert(doc);

		doc.put("name", "lijm001");
		doc.put("age", 29);
		doc.put("time", new Date());
		coll.insert(doc);

		// insert函数也支持插入文档列表：
		// insert(List<DBObject> list)

		// 检索数据
		BasicDBObject cond = new BasicDBObject();
		cond.put("name", "lijm");
		DBObject ret = coll.findOne(cond);
		System.out.println(ret);

		// 检索所有数据
		DBCursor rct = coll.find(cond);
		while (rct.hasNext()) {
			System.out.println(rct.next());
		}
	}

}