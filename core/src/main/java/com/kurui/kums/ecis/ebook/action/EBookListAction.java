package com.kurui.kums.ecis.ebook.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.StringUtil;
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
			ebookListForm.setList(ebookBiz.list(ebookListForm));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("ebook", ebookListForm);
		return mapping.findForward("listEBook");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		try {
			String ebookId = request.getParameter("id");
			EBook ebook = ebookBiz.getEBookById(ebookId);
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

		String ebookId = ebookListForm.getId();
		if (StringUtil.isEmpty(ebookId) == false) {
			if(ebookListForm.getSelectedItems()!=null&&ebookListForm.getSelectedItems().length>0){
					ebookId = ebookListForm.getSelectedItems()[0] + "";				
			}
			
		}

		if (StringUtil.isEmpty(ebookId) == false) {
			EBook ebook = ebookBiz.getEBookById(ebookId);
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
		String id = "";
		Inform inf = new Inform();
		try {
			for (int i = 0; i < ebookListForm.getSelectedItems().length; i++) {
				id = ebookListForm.getSelectedItems()[i] + "";
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