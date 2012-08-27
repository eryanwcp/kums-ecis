package com.kurui.kums.ecis.ebook.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.ecis.ebook.EBook;
import com.kurui.kums.ecis.ebook.EBookListForm;

public interface EBookDAO {

	public List list(EBookListForm ebookForm) throws AppException;

	public void delete(String id) throws AppException;

	public String save(EBook ebook) throws AppException;

	public String update(EBook ebook) throws AppException;

	public EBook getEBookById(String ebookId) throws AppException;

	public List<EBook> getEBookList() throws AppException;

	public List<EBook> getEBookListByType(String ebookTypes)
			throws AppException;

	public List<EBook> getValidEBookList() throws AppException;
}
