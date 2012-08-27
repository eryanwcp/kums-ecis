package com.kurui.kums.ecis.ebook.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.kurui.kums.base.BaseAction;
import com.kurui.kums.base.Inform;
import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.util.NoUtil;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.ecis.ebook.EBook;
import com.kurui.kums.ecis.ebook.biz.EBookBiz;
import com.kurui.kums.right.UserRightInfo;

public class EBookAction extends BaseAction {
	private EBookBiz ebookBiz;

	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		EBook ebookForm = (EBook) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			EBook ebook = new EBook();
			ebook.setId(NoUtil.getCurrentRandomNo());
			ebook.setTitle(ebookForm.getTitle());
			ebook.setAuthor(ebookForm.getAuthor());
			ebook.setCatalog(ebookForm.getCatalog());
			ebook.setContent(ebookForm.getContent());
			// ebook.setMemo(ebookForm.getMemo());
			ebook.setType(ebookForm.getType());
			ebook.setStatus(ebookForm.getStatus());
			String result = ebookBiz.saveOrUpdate(ebook);

			if (StringUtil.isEmpty(result)==false) {
				return redirectList(ebook);
			} else {
				inf.setMessage("您添加数据失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		EBook ebookForm = (EBook) form;
		Inform inf = new Inform();
		UserRightInfo uri = (UserRightInfo) request.getSession().getAttribute(
				"URI");
		try {
			if (StringUtil.isEmpty(ebookForm.getId())==false) {
				EBook ebook = ebookBiz.getEBookById(ebookForm.getId());
				ebook.setTitle(ebookForm.getTitle());
				ebook.setAuthor(ebookForm.getAuthor());
				ebook.setCatalog(ebookForm.getCatalog());
				ebook.setContent(ebookForm.getContent());
				// ebook.setMemo(ebookForm.getMemo());
				ebook.setType(ebookForm.getType());
				ebook.setStatus(ebookForm.getStatus());

				String result = ebookBiz.saveOrUpdate(ebook);

				if (StringUtil.isEmpty(result)==false) {
					return redirectList(ebook);
				} else {
					inf.setMessage("您修改数据失败！");
				}
			} else {
				inf.setMessage("缺少ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常:" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward redirectList(EBook ebook) {
		ActionRedirect redirect = new ActionRedirect("/ebook/ebookList.do");
		redirect.addParameter("thisAction", "list");
		if (ebook != null) {
			redirect.addParameter("type", ebook.getType());
		}
		return redirect;
	}


	public void setEbookBiz(EBookBiz ebookBiz) {
		this.ebookBiz = ebookBiz;
	}
}