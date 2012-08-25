package com.kurui.kums.mongodb.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

public class MongoFileUtil {
	static DB imgDB = null;
	static GridFS gridFS = null;

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		imgDB = MongoDB.getMongoDB("localhost", "imgs");
		gridFS = new GridFS(imgDB);

		String fileName = "Architect-201205-by-InfoQ.pdf"; // 读取 的文件
		String fileNameO = "architect.pdf"; // 写入文件名称
		MongoFileUtil mon = new MongoFileUtil();
		File fileIN = new File("F:/" + fileName);
		File fileOUT = new File("c:/" + fileNameO);

		/**
		 * 将文件存入 MongoDB 数据库中
		 */
		mon.saveFile(fileIN, fileName);
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		/**
		 * 从MongoDB中读取数据，并且写入磁盘
		 */
		List<GridFSDBFile> list = mon.findFilesByName(fileName);
		GridFSDBFile gridFSDBFile = (GridFSDBFile) list.get(0);
		gridFSDBFile.writeTo(fileOUT);
		
		System.out.println(list.size());
	}

	/**
	 * 写入文件
	 * 
	 * @param fileName
	 */
	public void saveFile(File file, String fileName) {
		try {
			GridFSFile mongofile = gridFS.createFile(file);
			mongofile.put("filename", fileName);
			mongofile.put("uploadDate", new Date());
			mongofile.put("contentType",
					fileName.substring(fileName.lastIndexOf(".")));
			mongofile.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param fileName
	 */
	public List<GridFSDBFile> findFilesByName(String fileName) {
		List<GridFSDBFile> list = gridFS.find(fileName);
		
		return list;
	}
	
//	public List<GridFSDBFile> findFilesByContent(String content) {
//		
//		List<GridFSDBFile> list = gridFS.find(query);
//		
//		return list;
//	}

	// 我在一台32位的机器上启动MongoDB，并且加入了smallfiles 和 nssize启动参数，找了一个428M的
	// android-sdk-windows.rar文件，写入MongoDB数据库四次，每次写入的消耗时间为
	// 87360/69485/105595/40094，每次插入内存占用率一次比一次高，开始mongod进程占用256M，最后mongod进程占用了2568M内存，在我写入第五遍的时候，出现了以下的错误信息：
	// Tue May 18 00:36:59 Caught Assertion in insert , continuing
	// Tue May 18 00:36:59 insert imgs.fs.chunks exception userassert:can't map
	// file memory – mongo requires 64 bit build for larger datasets 0ms
	// mmap() failed for /work/mongodata/imgs.7 len:536870912 errno:12 Cannot
	// allocate memory
	// mmap failed with out of memory, if you're using 32-bits, then you
	// probably need to upgrade to 64
	//
	// 暂时没有64位的系统，近期会准备一台继续折腾。
}
