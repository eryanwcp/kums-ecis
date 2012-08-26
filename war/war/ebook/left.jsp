<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/Web-INF/tld/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="/Web-INF/tld/c.tld" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>left</title>
		<link href="../_css/reset.css" rel="stylesheet" type="text/css" />
		<link href="../_css/global.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" language="javascript" src="../_js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" language="javascript" src="../_js/goto.js"></script>
		<script type="text/javascript" language="javascript" src="../_js/base/menu.js"></script>

		<c:if test="${URI==null}">
			<script>top.location="../login.jsp" ;</script>
		</c:if>
	</head>
	<body>
		<div id="mainContainer">
			<div class="fixedSideBar"></div>
			<div id="sideBar">
				<div class="sideBarItem webAdmin">
					<span class="title" onClick="showUL('ulEBook')"><a href="#">知识库管理</a> </span>
					<ul id="ulEBook" class="contents">
						<li>
							<a href="<%=path%>/ebook/ebookList.do?thisAction=list" target="mainFrame">电子书列表</a>
						</li>
						
				</div>
			</div>
			<div class="closeSiseBar">
				<span class="btn"></span>
			</div>
		</div>
		<script type="text/javascript" language="javascript">
      	initMenu("sideBar");
		</script>
	</body>
</html>
