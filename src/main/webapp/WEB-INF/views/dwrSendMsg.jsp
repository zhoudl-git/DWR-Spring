<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>DWR消息推送--发送</title>
<!-- 路径从web.xml中配置,具体文件不需要特意引入真实的js，项目自动生成 -->
<!-- DwrDemo自动生成,名称对应dwr中配置的create:javascript,路径对应web.xml中配置的url-pattern -->
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/engine.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/util.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/dwr/interface/MessagePusher.js'></script>
<script type="text/javascript" src='<%=request.getContextPath() %>/js/jquery.js'></script>
<script type="text/javascript">
	function init(){
		dwr.engine.setActiveReverseAjax(true);
		dwr.engine.setNotifyServerOnPageUnload(true);
		//DwrDemo.onPageLoad();发送页面可以不需要添加ScriptSession
	}
	//页面加载时调用init函数
	window.onload = init;
	
	//通过dwr直接调用
	function send(userId){
		var msg = $("#msg").val();
		//sendMessage对应后台dwr.xml中对应param的类的方法tk.ljyuan71.dwr.MessagePusher.sendMessage
		if (!userId || !msg) return;
		MessagePusher.sendMessage(userId,msg);
	}
	
	//通过ajax调用
	function update() {
		$.ajax({
			type:'post',
			contentType:'application/json',
			date:{"userId":"0"},
			url:'/SpringMVC-Dwr/web/update.do',
			success:function(data){
				if(date.SUCCESS == '200'){
					alert("更新并发送消息成功!");
				}
			}
		})
	}

</script>
</head>
<body>
	<textarea id="msg" rows="10" cols="70"></textarea></br>
	<button onclick="send('0');">给所有人发送</button>
	<button onclick="send(1);">给人员1发送</button>
	<button onclick="send(2);">给人员2发送</button>
	<button onclick="update();">Ajax过程中发送</button></br></br>
	<span>通过访问页面：项目名称/web/dwrReceiveMsg.do?userId=2(人员假定id)接收消息</span>
</body>
</html>