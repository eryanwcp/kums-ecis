package com.kurui.kums.ecis.ebook.dao;

import java.util.List;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.ecis.ebook.EBook;
import com.kurui.kums.ecis.ebook.EBookListForm;

public interface EBookDAO {

	public List list(EBookListForm ebookForm) throws AppException;

	public void delete(long id) throws AppException;

	public long save(EBook ebook) throws AppException;

	public long update(EBook ebook) throws AppException;

	public EBook getEBookById(long ebookId) throws AppException;

	public List<EBook> getEBookList() throws AppException;

	public List<EBook> getEBookListByType(String ebookTypes)
			throws AppException;

	public List<EBook> getValidEBookList() throws AppException;
}
