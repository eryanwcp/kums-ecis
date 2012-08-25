package com.kurui.kums.ecis.test.ebook;

public class EBook {

	private String id;
	private String title="";
	private String author;
	
	private String content;

	public EBook() {
	}


	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public String getAuthor() {
		return author;
	}





	public void setAuthor(String author) {
		this.author = author;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	@Override
	public String toString() {
		return "EBook [id=" + id + ", title=" + title + ", author=" + author
				+ ", content=" + content + "]";
	}





	public EBook(String id, String title, String author, String content) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
	}

	
}
