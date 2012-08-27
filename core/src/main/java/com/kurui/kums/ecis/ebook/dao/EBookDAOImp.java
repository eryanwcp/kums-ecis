package com.kurui.kums.ecis.ebook.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.ecis.ebook.EBook;
import com.kurui.kums.ecis.ebook.EBookListForm;

public class EBookDAOImp  implements EBookDAO {
	private MongoTemplate mongoTemplate;

	public List list(EBookListForm ebookListForm) throws AppException {
		List<EBook> listEBook = mongoTemplate.getCollection("ebooks",
				EBook.class);
		
		if(listEBook!=null){
			ebookListForm.setTotalRowCount(listEBook.size());
		}
		
		return listEBook;
	}
	
	

	public void delete(String id) throws AppException {
		if (StringUtil.isEmpty(id)==false) {
//			EBook ebook = (EBook) this.getHibernateTemplate().get(
//					EBook.class, new Long(id));
//			this.getHibernateTemplate().delete(ebook);
		}
	}

	public String save(EBook ebook) throws AppException {
		mongoTemplate.save(ebook);
		return ebook.getId();
	}

	public String update(EBook ebook) throws AppException {
		if (StringUtil.isEmpty(ebook.getId())==false) {
//			mongoTemplate.update(ebook);
			return ebook.getId();
		} else{
			throw new IllegalArgumentException("id isn't a valid argument.");
			
		}
	}

	public EBook getEBookById(String id) throws AppException {
		EBook ebook = mongoTemplate.findOne("ebooks",
				new Query(Criteria.where("id").is(id+"")), EBook.class);
		return ebook;
	}

	public List<EBook> getEBookList() throws AppException {
		List<EBook> list = mongoTemplate.getCollection("ebooks",
				EBook.class);
		return list;
	}

	public List<EBook> getEBookListByType(String ebookTypes)
			throws AppException {
		List<EBook> list = new ArrayList<EBook>();
		Hql hql = new Hql();
		hql.add(" from EBook p where  1=1 ");

		if (Constant.toString(ebookTypes) != "") {
			hql.add(" and p.type in(" + ebookTypes + ")");
		}

		hql.add(" and p.status=" + EBook.STATES_1);
		hql.add(" order by p.type ");
//		Query query = this.getQuery(hql);
//		if (query != null) {
//			list = query.list();
//			if (list != null && list.size() > 0) {
//				return list;
//			}
//		}
		return list;
	}

	public List<EBook> getValidEBookList() throws AppException {
		List<EBook> list = new ArrayList<EBook>();
		Hql hql = new Hql();
		hql.add(" from EBook p where 1=1 ");
		hql.add(" and p.status=" + EBook.STATES_1);
		hql.add(" order by p.type ");
//		Query query = this.getQuery(hql);
//		if (query != null) {
//			list = query.list();
//			if (list != null && list.size() > 0) {
//				return list;
//			}
//		}
		return list;
	}



	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	
}
