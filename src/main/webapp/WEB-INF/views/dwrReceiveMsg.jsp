<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>DWR消息推送--接收</title>
<!-- 路径从web.xml中配置,具体文件不需要特意引入真实的js，项目自动生成 -->
<!-- DwrDemo自动生成,名称对应dwr中配置的create:javascript,路径对应web.xml中配置的url-pattern -->
<script type="text/javascript" src='<%=request.getContextPath()  %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()  %>/dwr/util.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()  %>/dwr/interface/MessagePusher.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/js/jquery.js'></script>
<script type="text/javascript">
	var userId =  '${param.userId}';
	function init(){
		if (userId || userId != ''){
			dwr.engine.setActiveReverseAjax(true);
			dwr.engine.setNotifyServerOnPageUnload(true);
			MessagePusher.onPageLoad(userId);
		}
	}
	//页面加载时调用init函数
	window.onload = init;
	//对应appendCall设置的方法名称
	function showMessage(msg) {
		$("#msg").val(msg);
		alert(msg);
	}
</script>
</head>
<body>
	<span>切换用户时可以在url后添加userId参数及其值</span></br>
	<span>当前用户id: ${param.userId }</span></br>
	<textarea id="msg" rows="10" cols="70"></textarea>
</body>
</html>