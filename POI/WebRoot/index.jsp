<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("ctx", basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${ctx}statics/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}statics/js/bootstrap.min.js"></script>
</head>
<style>
.my_btn_notright {
	border-top-right-radius: 0;
	border-bottom-right-radius: 0;
}

.my_btn_notleft {
	border-top-left-radius: 0;
	border-bottom-left-radius: 0;
}
</style>
<body>
	<a class="btn btn-danger" href="bill/export">导出</a>
	<div class="container">
		<!-- 表头 -->
		<div class="row">
			<a href="javascript:void(0)"
				class="btn btn-info col-md-2 my_btn_notright">账单编号</a> <a
				href="javascript:void(0)"
				class="btn btn-info col-md-2 my_btn_notright my_btn_notleft">商品名
			</a> <a href="javascript:void(0)"
				class="btn btn-info col-md-2 my_btn_notright my_btn_notleft">商品数量</a>
			<a href="javascript:void(0)"
				class="btn btn-info col-md-2 my_btn_notright my_btn_notleft">账单金额</a>
			<a href="javascript:void(0)"
				class="btn btn-info col-md-2 my_btn_notright my_btn_notleft">供应商</a>
			<a href="javascript:void(0)"
				class="btn btn-info col-md-2 my_btn_notleft">创建时间</a>
		</div>
		<c:forEach items="${requestScope.list}" var="item">
			<div class="row">
				<a href="javascript:void(0)"
					class="btn btn-default col-md-2 my_btn_notright">${item.id}</a> <a
					href="javascript:void(0)"
					class="btn btn-default col-md-2 my_btn_notright my_btn_notleft">${item.tradeName}
				</a> <a href="javascript:void(0)"
					class="btn btn-default col-md-2 my_btn_notright my_btn_notleft">${item.num}</a>
				<a href="javascript:void(0)"
					class="btn btn-default col-md-2 my_btn_notright my_btn_notleft">${item.amount}</a>
				<a href="javascript:void(0)"
					class="btn btn-default col-md-2 my_btn_notright my_btn_notleft">${item.supplier }</a>
				<a href="javascript:void(0)"
					class="btn btn-default col-md-2 my_btn_notleft">
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
					</a>
			</div>
		</c:forEach>
	</div>
</body>

</html>
