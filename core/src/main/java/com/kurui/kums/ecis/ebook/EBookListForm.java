package com.kurui.kums.ecis.ebook;

import com.kurui.kums.base.ListActionForm;

public class EBookListForm extends ListActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id = Long.valueOf(0);
	
	private String keywords="";

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	private String thisAction = "";

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

	private int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	

}
