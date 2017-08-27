<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
<script type="text/javascript">
function deleteItems(){
	document.itemsForm.action="${pageContext.request.contextPath }/ItemsInfo/deleteItems.action";
	document.itemsForm.submit();
}


function queryItems(){
	document.itemsForm.action="${pageContext.request.contextPath }/ItemsInfo/deleteItems.action";
	document.itemsForm.submit();
}
</script>
</head>
<body> 
用户：${username}
<c:if test="${username !=null}">
	<a href="${pageContext.request.contextPath }/logout.action">退出</a>
</c:if>
<form name="itemsForm" action="${pageContext.request.contextPath }/ItemsInfo/queryItems.action" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td>商品名称： <input type="text" name="itemsCustom.name"/></td>
<td>分类：
<select name="">
	<c:forEach items="${itemTypes}" var="itemtype" >
		<option value="${itemtype.key}">${itemtype.value}</option>
	</c:forEach>
</select>
</td>
</tr>
<tr>
<td><input type="button" value="查询" onclick="queryItems()"/></td>
<td><input type="button" value="批量删除" onclick="deleteItems()"/></td>
</tr>
</table>
商品列表：
<table width="100%" border=1>
<tr>
	<td>选择</td>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
<c:forEach items="${items}" var="itemsCustom">
<tr>
	<td><input type="checkBox" name="items_id" value="${itemsCustom.id}"/></td>
	<td>${itemsCustom.name }</td>
	<td>${itemsCustom.price }</td>
	<td><fmt:formatDate value="${itemsCustom.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${itemsCustom.detail }</td>
	<td><a href="${pageContext.request.contextPath}/ItemsInfo/editItems.action?id=${itemsCustom.id}">修改</a></td>

</tr>
</c:forEach>

</table>
</form>
</body>

</html>