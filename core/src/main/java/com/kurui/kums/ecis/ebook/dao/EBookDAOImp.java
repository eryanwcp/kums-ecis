package com.kurui.kums.ecis.ebook.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.document.mongodb.MongoTemplate;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.database.Hql;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.ecis.ebook.EBook;
import com.kurui.kums.ecis.ebook.EBookListForm;

public class EBookDAOImp  implements EBookDAO {
	private MongoTemplate mongoTemplate;

	public List list(EBookListForm ebookListForm) throws AppException {
		List<EBook> listEBook = mongoTemplate.getCollection("ebooks",
				EBook.class);
		return listEBook;
	}
	
	

	public void delete(long id) throws AppException {
		if (id > 0) {
//			EBook ebook = (EBook) this.getHibernateTemplate().get(
//					EBook.class, new Long(id));
//			this.getHibernateTemplate().delete(ebook);
		}
	}

	public long save(EBook ebook) throws AppException {
//		this.getHibernateTemplate().save(ebook);
		return ebook.getId();
	}

	public long update(EBook ebook) throws AppException {
		if (ebook.getId() > 0) {
//			this.getHibernateTemplate().update(ebook);
			return ebook.getId();
		} else
			throw new IllegalArgumentException("id isn't a valid argument.");
	}

	public EBook getEBookById(long id) throws AppException {
		Hql hql = new Hql();
		hql.add("from EBook p where p.id=" + id);

//		Query query = this.getQuery(hql);
//		EBook ebook = null;
//		if (query != null && query.list() != null && query.list().size() > 0) {
//			ebook = (EBook) query.list().get(0);
//		}
		EBook ebook=new EBook();
		return ebook;
	}

	public List<EBook> getEBookList() throws AppException {
		List<EBook> list = new ArrayList<EBook>();
		Hql hql = new Hql();
		hql.add(" from EBook p  where 1=1 ");
		hql.add(" and p.status=" + EBook.STATES_1);
		hql.add(" order by p.type ");
//		return this.list(hql);
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
