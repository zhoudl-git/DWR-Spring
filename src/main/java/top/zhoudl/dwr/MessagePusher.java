package top.zhoudl.dwr;

import java.util.Collection;

import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.WebContextFactory;

/**
 * @author zhoudongliang
 * @create 2018/7/17
 **/
public class MessagePusher {

	final public static String SCRIPT_SESSION_USERID = "SCRIPT_SESSION_USERID";
	final public static String SCRIPT_SESSION_MSG = "showMessage";
	//这是页面上当后台消息推送时，自动触发的js方法名称


	public void onPageLoad(String userId) {
        if (StringUtils.isNotBlank(userId)){
        	ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        	scriptSession.setAttribute("userId", userId);
        	System.out.println("添加了一个scriptSession：  "+userId);
        }
        /*DwrScriptSessionManagerUtil dwrScriptSessionManagerUtil = new DwrScriptSessionManagerUtil();
		try {
			dwrScriptSessionManagerUtil.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}*/
	}

	/**
	 * 采用dwr的方式向前台推送消息
	 * @param userid 用户Id
	 * @param message 消息内容
	 */
	public void sendMessage(final String userid, final String message) {
		final String id = userid;
        final String msg = message;
		Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
			//如果返回true,那将匹配的ScriptSession添加到Browser.getTargetSessions()中,待后面调用
			public boolean match(ScriptSession session) {
				String userId = (String) session.getAttribute("userId");
				if (userId == null) {
					return false;
				} else if ("0".equalsIgnoreCase(id)) {
					return true;
				} else {
					return userId.equalsIgnoreCase(id);
				}
			}
		}, new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();
			@Override
			public void run() {
				//对应页面调用的方法名称及参数
				script.appendCall(SCRIPT_SESSION_MSG, msg);
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		});
	}
}
