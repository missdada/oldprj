package manytag.framework.authz.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import manytag.framework.authz.Permission;
import manytag.framework.authz.SimpleAuthorizationInfo;
import manytag.framework.authz.permission.WildcardPermission;
import manytag.framework.util.CollectionUtils;
import manytag.framework.util.PermissionUtils;

public class AuthzUser implements Serializable {
	private static final long serialVersionUID = -3444828228215249689L;

	protected SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

	public Set<String> getStringPermissions() {
		return authorizationInfo.getStringPermissions();
	}

	public void setStringPermissions(Set<String> stringPermissions) {
		authorizationInfo.setStringPermissions(stringPermissions);
	}

	public void addStringPermission(String permission) {

		authorizationInfo.addStringPermission(permission);
	}

	public void addStringPermissions(Collection<String> permissions) {
		authorizationInfo.addStringPermissions(permissions);
	}

	public Set<String> getRoles() {
		return authorizationInfo.getRoles();
	}

	public void setRoles(Set<String> roles) {
		authorizationInfo.setRoles(roles);
	}

	public void addRole(String role) {
		authorizationInfo.addRole(role);
	}

	public void addRoles(Collection<String> roles) {
		authorizationInfo.addRoles(roles);
	}

	public boolean isPermitted(String p) {
		Permission permission = new WildcardPermission(p);
		return isPermitted(permission);
	}

	public boolean isPermitted(Permission permission) {
		Collection<Permission> perms = getPermissions();
		if (perms != null && !perms.isEmpty()) {
			for (Permission perm : perms) {
				if (perm.implies(permission)) {
					return true;
				}
			}
		}
		return false;
	}

	private Collection<Permission> getPermissions() {
		Set<Permission> permissions = new HashSet<Permission>();
		if (authorizationInfo.getStringPermissions() != null) {
			if (authorizationInfo.getStringPermissions().size() > 0) {
				Set<Permission> perms = PermissionUtils.resolvePermissions(authorizationInfo.getStringPermissions());
				if (!CollectionUtils.isEmpty(perms)) {
					permissions.addAll(perms);
				}
			}
		}

		if (permissions.isEmpty()) {
			return Collections.emptySet();
		} else {
			return Collections.unmodifiableSet(permissions);
		}
	}
}