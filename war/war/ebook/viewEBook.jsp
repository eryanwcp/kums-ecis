<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>main</title>
		<link href="<%=path%>/_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/_css/global.css" rel="stylesheet"
			type="text/css" />
		<script src="<%=path%>/_js/base/FormUtil.js" type="text/javascript"></script>
	</head>
	<body>
		<div id="mainContainer">
			<div id="container">
				<html:form action="/ebook/ebookList.do">
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="10" height="10" class="tblt"></td>
							<td height="10" class="tbtt"></td>
							<td width="10" height="10" class="tbrt"></td>
						</tr>
						<tr>
							<td width="10" class="tbll"></td>
							<td valign="top" class="body">
								<c:import url="../page/mainTitle.jsp" charEncoding="UTF-8">
									<c:param name="title1" value="客户管理" />
									<c:param name="title2" value="查看客户信息" />
								</c:import>
								<hr>
								<table width="100%" cellpadding="0" cellspacing="0" border="0"
									class="dataList">
									<tr>
										<td class="lef">
											标题
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${ebook.title}" />											
										</td>
									</tr>
										<tr>
										<td class="lef">
											作者
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${ebook.author}" />											
										</td>
											<tr>
										<td class="lef">
											目录
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${ebook.catalog}" />											
										</td>
											<tr>
										<td class="lef">
											内容
										</td>
										<td style="text-align: left" colspan="3">
											<c:out value="${ebook.content}" />											
										</td>
										
									</tr>
								
								</table>
								<table width="100%" style="margin-top: 5px;">
									<tr>
										<td align="center" colspan="4">
											<input name="label" type="button" class="button1" value="返 回"
												onclick="window.history.back();">
											<input name="label" type="button" class="button1" value="新 增"
												onclick="add();">
											<input name="label" type="button" class="button1" value="编 辑"
												onclick="edit(<c:out value='${ebook.id}' />);">
										</td>
									</tr>
								</table>
								<div class="clear"></div>
							</td>
							<td width="10" class="tbrr"></td>
						</tr>
						<tr>
							<td width="10" class="tblb"></td>
							<td class="tbbb"></td>
							<td width="10" class="tbrb"></td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
		<script type="text/javascript">
		function add(){
   			 var url="../ebook/ebookList.do?thisAction=save";
    		 window.location.href=url;
 		}
		function edit(id){
   			 var url="../ebook/ebookList.do?thisAction=edit&id="+id;
    		 window.location.href=url;
 		}
 		
		</script>
	</body>
</html>