﻿<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>main</title>
<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
<link href="../_css/global.css" rel="stylesheet" type="text/css" />
<script src="../_js/prototype/common.js" type="text/javascript"></script>
<script src="../_js/base/FormUtil.js" type="text/javascript"></script>
<script src="../_js/xheditor.js" type="text/javascript"></script>
<script src="../_js/xheditor_plugins/ubb.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
	<!--
	function addEditorValue(){
				document.forms[0].catalog.value= window.content1.getHTML(); 		
			}
			
			function setEditorHTML(){
			//alert("aaa")
				window.content1.setHTML(document.forms[0].catalog.value);
			}
		-->	
		function add(){	
			var title=document.forms[0].title.value;
			if(title==""){
				alert("请输入标题")
				return false;
			}
			
			//addEditorValue();
			
			var thisAction =document.forms[0].thisAction.value;			   
		    document.forms[0].action="<%=path%>/ebook/ebook.do?thisAction="+thisAction;
		    document.forms[0].submit();
		}		
		
			
		
	</script>
	<!-- 
<body onLoad="setEditorHTML();">
 -->
 <body>
	<div id="mainContainer">
		<div id="container">
			<html:form action="/ebook/ebook.do" method="post">
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td width="10" height="10" class="tblt"></td>
						<td height="10" class="tbtt"></td>
						<td width="10" height="10" class="tbrt"></td>
					</tr>
					<tr>
						<td width="10" class="tbll"></td>
						<td valign="top" class="body">
							<table width="100%" cellpadding="0" cellspacing="0" border="0"
								class="dataList">
								<tr>
									<td class="lef">标题</td>
									<td style="text-align: left" colspan="3"><html:text
											property="title" name="title" value="${ebook.title}"
											styleClass="colorblue2 p_5" style="width:800px;"></html:text>
										<html:hidden property="id" value="${ebook.id}"></html:hidden>

										<html:hidden property="thisAction" name="ebook" /> <input
										name="label" type="button" class="button1" value="返 回"
										onclick="window.history.back();"> <input name="label"
										type="button" class="button1" value="提交" onclick="add();">
										<input name="label" type="reset" class="button1" value="重 置">

									</td>
								</tr>
								<tr>
									<td class="lef">作者</td>
									<td style="text-align: left" colspan="3"><html:text
											property="author" name="ebook" styleClass="colorblue2 p_5"
											style="width:200px;"></html:text></td>
								</tr>
								<tr>
									<td class="lef">目录</td>
									<td style="text-align: left" colspan="3">
										 <html:textarea
											property="catalog" name="ebook" cols="120" rows="20"
											value="${ebook.catalog}"></html:textarea>
										<!--	 <html:hidden property="catalog" name="ebook"></html:hidden>
										<iframe ID="eWebEditor1" name="content1"
											src="../ewebeditor/ewebeditor.htm?id=content1&style=coolblue"
											frameborder="1" scrolling="auto" align="top" width="680"
											height="500" />
											 -->
									</td>
								</tr>
								<tr>
									<td class="lef">类型</td>
									<td style="text-align: left"><html:select property="type"
											name="ebook" styleClass="colorblue2 p_5" style="width:50px;">
											<html:option value="1">类型1</html:option>
											<html:option value="2">类型2</html:option>
										</html:select></td>
								</tr>
								<tr>
									<td class="lef">状态</td>
									<td style="text-align: left"><html:select
											property="status" name="ebook" styleClass="colorblue2 p_5"
											style="width:50px;">
											<html:option value="1">有效</html:option>
											<html:option value="0">无效</html:option>
										</html:select></td>
								</tr>
							</table>
							<table width="100%" style="margin-top: 5px;">
								<tr>
									<td align="center" colspan="4"><input name="label"
										type="button" class="button1" value="返 回"
										onclick="window.history.back();"> <input name="label"
										type="button" class="button1" value="提交" onclick="add();">
										<input name="label" type="reset" class="button1" value="重 置">
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

</body>
</html>


