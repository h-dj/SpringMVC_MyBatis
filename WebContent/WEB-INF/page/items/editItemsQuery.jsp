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
function updateItemsList(){
	document.itemsForm.action="${pageContext.request.contextPath }/ItemsInfo/updateItemsList.action";
	document.itemsForm.submit();
}


function editItemsQuery(){
	document.itemsForm.action="${pageContext.request.contextPath }/ItemsInfo/editItemsQuery.action";
	document.itemsForm.submit();
}
</script>
</head>
<body> 
<form name="itemsForm" action="${pageContext.request.contextPath }/ItemsInfo/queryItems.action" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td>商品名称： <input type="text" name="itemsCustom.name"/></td>
</tr>
<tr>
<td><input type="button" value="查询" onclick="editItemsQuery()"/></td>
<td><input type="button" value="批量修改" onclick="updateItemsList()"/></td>
</tr>
</table>
商品列表：
<table width="100%" border=1>
<tr>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
</tr>
<c:forEach items="${items}" var="itemsCustom" varStatus="status">
<tr>
	<input type="hidden" name="itemList[${status.index }].id" value="${itemsCustom.id}" />
	<td><input type="text" name="itemList[${status.index }].name" value="${itemsCustom.name}" /></td>
	<td><input type="text" name="itemList[${status.index }].price" value="${itemsCustom.price }" /></td>
	<td><input type="text" name="itemList[${status.index }].createtime" value="<fmt:formatDate value="${itemsCustom.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
	<td><input type="text" name="itemList[${status.index }].detail" value="${itemsCustom.detail }" /></td>
</tr>
</c:forEach>

</table>
</form>
</body>

</html>