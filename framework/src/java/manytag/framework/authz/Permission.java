package manytag.framework.authz;

/**
 * 权限接口
 * 
 */
public interface Permission {
	/**
	 * 权限验证
	 * @param p 待验证的权限
	 * @return 验证结果 true：有此权限，false：无此权限
	 */
	boolean implies(Permission p);
}