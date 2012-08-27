package com.kurui.kums.ecis.ebook.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.ecis.ebook.EBook;
import com.kurui.kums.ecis.ebook.EBookListForm;
import com.kurui.kums.ecis.ebook.biz.EBookBiz;

public class EBookListAction extends BaseAction {
	private EBookBiz ebookBiz;
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EBookListForm ebookListForm = (EBookListForm) form;
		if (ebookListForm == null) {
			ebookListForm = new EBookListForm();
		}
		try {
			ebookListForm.setList(ebookBiz
					.list(ebookListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("ebook", ebookListForm);
		return mapping.findForward("listEBook");
	}
	
	/***************************************************************************
	 * 选择产品
	 **************************************************************************/
	public ActionForward selectFinanceEBook(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		EBookListForm ebookListForm = (EBookListForm) form;
		ebookListForm.setList(ebookBiz.list(ebookListForm));
		ebookListForm.setThisAction("selectFinanceEBook");
		
		request.setAttribute("ebookListForm", ebookListForm);
//		System.out.println("rowId:"+ebookListForm.getRowId());
		forwardPage = "listEBookSelect";
		return (mapping.findForward(forwardPage));
	}
	public static void main(String[] args) {
		String aa="5,8，";
		aa=aa.substring(0,aa.lastIndexOf(","));
		System.out.println(aa);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		try {
			String ebookId = request.getParameter("id");
			EBook ebook = ebookBiz
					.getEBookById(Long.parseLong(ebookId));
			request.setAttribute("ebook", ebook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		forwardPage = "viewEBook";
		return mapping.findForward(forwardPage);

	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EBook ebook = new EBook();
		ebook.setType(EBook.TYPE_1);
		ebook.setThisAction("insert");
		request.setAttribute("ebook", ebook);
		String forwardPage = "editEBook";
		return mapping.findForward(forwardPage);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EBookListForm ebookListForm = (EBookListForm) form;
		
		long ebookId = ebookListForm.getId();
		if (ebookId < 1) {
			ebookId = ebookListForm.getSelectedItems()[0];
		}
		
		if (ebookId > 0) {
			EBook ebook = ebookBiz
					.getEBookById(ebookId);
			ebook.setThisAction("update");
			request.setAttribute("ebook", ebook);
		} else {
			request.setAttribute("ebook", new EBook());
		}
		return mapping.findForward("editEBook");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EBookListForm ebookListForm = (EBookListForm) form;
		// String forwardPage = "";
		long id = 0;
		Inform inf = new Inform();
		try {
			for (int i = 0; i < ebookListForm.getSelectedItems().length; i++) {
				id = ebookListForm.getSelectedItems()[i];
				ebookBiz.deleteEBook(id);
			}
			return list(mapping, form, request, response);
		} catch (Exception ex) {
			inf.setMessage("删除失败" + ex.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public void setEbookBiz(EBookBiz ebookBiz) {
		this.ebookBiz = ebookBiz;
	}


}