<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>json交互的两种方式</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
</head>
<script type="text/javascript">
//请求json,接收json
	function requestJson(){
	alert("123");
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/requestJson.action",
			   contentType:"application/json;charset=utf-8",
			   data: '{"name":"手机","price":"999"}',
			   success: function(data){
			     alert( data.toString());
			   }
			});
	}
	//请求key/value,接收json
	function requestKeyValue(){
		$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath}/requestKeyValue.action",
			   contentType:"application/x-www-form-urlencoded",
			   data: 'name=手机&price=999',
			   success: function(data){
			     alert( data.toString());
			   }
			});
	}
</script>
<body>
<input type="button" value="提交json,接收json" onclick="requestJson()" />
<input type="button" value="提交key/value,接收json" onclick="requestKeyValue()"/>
</body>
</html>