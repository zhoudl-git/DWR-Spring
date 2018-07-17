package top.zhoudl.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import top.zhoudl.dwr.MessagePusher;

@Controller
@RequestMapping("/web")
public class SpringController {
	private static Logger log = Logger.getLogger(SpringController.class);
	
	@RequestMapping("/spring")
	@ResponseBody
	public String springWeb(HttpServletRequest request,HttpServletResponse response){
		log.info("=======进入了SpringController的springWeb方法=======");
		
		return "Hello World 访问到了";
	}
	
	@RequestMapping("/viewResolver")
	@ResponseBody
	public ModelAndView viewResolver(HttpServletRequest request,HttpServletResponse response){
		log.info("=======进入了SpringController的viewResolver方法=======");
		//通过spring-mvc.xml中的jsp视图解析器配置解析成/WEB-INF/views/+jspPath+.jsp
		//为了安全，WEB-INF下的jsp是不对外可见的，只能forward能访问到
		String jspPath = "/index";
		ModelAndView mv = new ModelAndView(jspPath);
		return mv;
	}
	@RequestMapping("/dwrSendMsg")
	@ResponseBody
	public ModelAndView dwrSendMsg(HttpServletRequest request,HttpServletResponse response){
		String jspPath = "/dwrSendMsg";
		ModelAndView mv = new ModelAndView(jspPath);
		return mv;
	}
	@RequestMapping("/dwrReceiveMsg")
	@ResponseBody
	public ModelAndView dwrReceiveMsg(HttpServletRequest request,HttpServletResponse response){
		String jspPath = "/dwrReceiveMsg";
		ModelAndView mv = new ModelAndView(jspPath);
		return mv;
	}
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		MessagePusher push = new MessagePusher();
		push.sendMessage("2", "这是通过Ajax请求生成的消息,发送给2用户");
		map.put("SUCCESS", 200);
		return map;
	}
	
}