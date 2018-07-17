package top.zhoudl.dwr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.servlet.DwrServlet;

/**
 * @author zhoudongliang
 * @create 2018/7/17
 **/
public class DwrScriptSessionManagerUtil extends DwrServlet {

	/** */
	private static final long serialVersionUID = -8778373469311495523L;

	/**
	 * 初始化dwr监听，只在程序中调用一次即可
	 */
	@Override
	public void init() throws ServletException {
		Container container = ServerContextFactory.get().getContainer();
		ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
		ScriptSessionListener listener = new ScriptSessionListener() {
			@Override
			public void sessionCreated(ScriptSessionEvent ev) {
				//从session中获取用户
				HttpSession session = WebContextFactory.get().getSession();
				User user = ((User) session.getAttribute("user"));
				if (user != null) {
					String userId = user.getUserId();
					System.out.println("创建了一个新的ScriptSession: "+userId);
					ev.getSession().setAttribute("userId", userId);
				}
			}
			@Override
			public void sessionDestroyed(ScriptSessionEvent ev) {
				System.out.println("销毁了一个ScriptSession");
			}
		};

		manager.addScriptSessionListener(listener);

	}

}
