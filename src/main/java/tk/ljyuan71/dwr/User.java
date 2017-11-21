package tk.ljyuan71.dwr;

/**
 * @author ljyuan 2017年11月20日
 * @Description:  dwr模拟用户表
 */
public class User {
	//用户id
	private String userId;
	//用户名称
	private String userName;
	//用户密码
	private String password;
	/** 获取: 用户id 值  */
	public String getUserId() {
		return userId;
	}
	/** 设置: 用户id 值 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/** 获取: 用户名称 值  */
	public String getUserName() {
		return userName;
	}
	/** 设置: 用户名称 值 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/** 获取: 用户密码 值  */
	public String getPassword() {
		return password;
	}
	/** 设置: 用户密码 值 */
	public void setPassword(String password) {
		this.password = password;
	}

}
