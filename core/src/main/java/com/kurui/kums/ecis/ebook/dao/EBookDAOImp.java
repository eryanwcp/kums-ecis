package com.kurui.kums.ecis.ebook.dao;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.ecis.ebook.EBook;
import com.kurui.kums.ecis.ebook.EBookListForm;

public class EBookDAOImp  implements EBookDAO {
	private MongoTemplate mongoTemplate;

	public List list(EBookListForm ebookListForm) throws AppException {
		
		String keywords=ebookListForm.getKeywords();
		//in 全部包含
		//regex 正则匹配
		Criteria criteriaTitle=Criteria.where("title").regex(".*?"+keywords+".*");
		Criteria criteriaCatalog=Criteria.where("catalog").regex(".*?"+keywords+".*");
		Criteria criteriaContent=Criteria.where("content").regex(".*?"+keywords+".*");
		Query query=new Query(criteriaCatalog).limit(10);
		
//		Pattern pattern = Pattern.compile("^.*" + keywords+ ".*$",Pattern.CASE_INSENSITIVE);  

//		query.filter("name", pattern);  
//		List<EBook> persons = query.asList();  
//		测试了一下不怎么好用，如果把pattern修改为如下内容就可以了
//	pattern = Pattern.compile(".*" + keywords+ ".$",keywords+ "%' " ,Pattern.CASE_INSENSITIVE);  

//		即：去掉正则表达式的^和$就能匹配所有data了
	
		
		List<EBook> listEBook = mongoTemplate.find("ebook",query,
				EBook.class);
		
		if(listEBook!=null){
			ebookListForm.setTotalRowCount(listEBook.size());
		}
		
		return listEBook;
	}
	
	

	public void delete(String id) throws AppException {
		if (StringUtil.isEmpty(id)==false) {
			mongoTemplate.findAndRemove("ebook", getEBookByIdQuery(id), EBook.class);
			
		}
	}

	public String saveOrUpdate(EBook ebook) throws AppException {
		mongoTemplate.save("ebook",ebook);
//		mongoTemplate.save("ebook",ebook);//当记录不存在时插入，或者是当记录已存在是更新，实际上就是saveorupdate的意思。

//		mongoTemplate.insert(ebook);//当记录不存在时插入，而如果记录存在时则忽略，继续插入。

		return ebook.getId();
	}



	public EBook getEBookById(String id) throws AppException {
		EBook ebook = mongoTemplate.findOne("ebook",
				new Query(Criteria.where("id").is(id)), EBook.class);
		return ebook;
	}
	
	public Query getEBookByIdQuery(String id){
		return new Query(Criteria.where("id").is(id));
	}

	public List<EBook> getEBookList() throws AppException {
		List<EBook> list = mongoTemplate.getCollection("ebook",
				EBook.class);
		return list;
	}

	public List<EBook> getEBookListByType(String ebookTypes)
			throws AppException {
		List<EBook> list =mongoTemplate.find("ebook",new Query(Criteria.where("type").in(ebookTypes)),
				EBook.class);
		return list;
	}

	public List<EBook> getValidEBookList() throws AppException {
		List<EBook> list =mongoTemplate.find("ebook",new Query(Criteria.where("status").is(EBook.STATES_1)),
				EBook.class);
		return list;
	}



	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	
}
