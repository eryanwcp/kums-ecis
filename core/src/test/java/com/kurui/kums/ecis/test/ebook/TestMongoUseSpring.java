package com.kurui.kums.ecis.test.ebook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoOperations;

public class TestMongoUseSpring extends TestCase{
	
	public static void testReadTxt() {
		
		String content="";
		try {
			content = readTxt("D:\\Test.txt", "GBK");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(content);
	}
	

//	public static void testEBook() {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext(
//				"com/kurui/kums/ecis/test/mongodb-config.xml");
//		MongoOperations mongoOperation = (MongoOperations) ctx
//				.getBean("mongoTemplate");
//		
//		String content="";
//		try {
//			content = readTxt("D:\\Test.txt", "GBK");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		EBook ebook = new EBook("100", "infoq", "Architect-201204-by-InfoQ.pdf", content);
//		// save
//		mongoOperation.save("ebooks", ebook);
//
//
//		// List
//		List<EBook> listEBook = mongoOperation.getCollection("ebooks",
//				EBook.class);
//		System.out.println("size of listEBook = " + listEBook.size());
//
//	}


	

	public  static String readTxt(String filePathAndName, String encoding)
			throws IOException {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		try {
			FileInputStream fs = new FileInputStream(filePathAndName);
			InputStreamReader isr;
			if (encoding.equals(""))
				isr = new InputStreamReader(fs);
			else
				isr = new InputStreamReader(fs, encoding);
			BufferedReader br = new BufferedReader(isr);
			try {
				for (String data = ""; (data = br.readLine()) != null;)
					str.append((new StringBuilder(String.valueOf(data)))
							.append(" ").toString());

			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
		} catch (IOException es) {
			st = "";
		}
		return st;
	}
}
