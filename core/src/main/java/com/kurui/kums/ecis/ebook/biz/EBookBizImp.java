package com.kurui.kums.ecis.ebook.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.ecis.ebook.EBook;
import com.kurui.kums.ecis.ebook.EBookListForm;
import com.kurui.kums.ecis.ebook.dao.EBookDAO;

public class EBookBizImp implements EBookBiz {
	private EBookDAO ebookDAO;

	public List list(EBookListForm ebookForm) throws AppException {
		return ebookDAO.list(ebookForm);
	}

	public void delete(long id) throws AppException {
		try {
			ebookDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEBook(Long ebookId) throws AppException {
		EBook ebook = getEBookById(ebookId);
		ebook.setStatus(EBook.STATES_0);// 将状态变为无效
		update(ebook);
	}

	public long save(EBook ebook) throws AppException {
		return ebookDAO.save(ebook);
	}

	public long update(EBook ebook) throws AppException {
		return ebookDAO.update(ebook);
	}

	public EBook getEBookById(long ebookId) throws AppException {
		return ebookDAO.getEBookById(ebookId);
	}

	public List<EBook> getEBookList() throws AppException {
		return ebookDAO.getEBookList();
	}

	public List<EBook> getEBookListByType(String ebookTypes)
			throws AppException {
		return ebookDAO.getEBookListByType(ebookTypes);
	}

	public List<EBook> getValidEBookList() throws AppException {
		return ebookDAO.getValidEBookList();
	}

	public void setEbookDAO(EBookDAO ebookDAO) {
		this.ebookDAO = ebookDAO;
	}



}
