package com.kurui.kums.ecis.ebook.biz;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.ecis.ebook.EBook;
import com.kurui.kums.ecis.ebook.EBookListForm;

public interface EBookBiz {

	public List list(EBookListForm form) throws AppException;

	public void delete(String id) throws AppException;

	public void deleteEBook(String ebookId) throws AppException;

	public String saveOrUpdate(EBook ebook) throws AppException;

	public EBook getEBookById(String ebookId)
			throws AppException;

	public List<EBook> getEBookList() throws AppException;

	public List<EBook> getEBookListByType(
			String ebookTypes) throws AppException;

	public List<EBook> getValidEBookList()
			throws AppException;

}
