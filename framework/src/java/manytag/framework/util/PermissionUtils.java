package manytag.framework.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import manytag.framework.authz.Permission;
import manytag.framework.authz.permission.WildcardPermission;

public class PermissionUtils {
	public static Set<Permission> resolveDelimitedPermissions(String permissionsString) {
		Set<String> permStrings = toPermissionStrings(permissionsString);
		return resolvePermissions(permStrings);
	}

	public static Set<String> toPermissionStrings(String permissionsString) {
		String[] tokens = StringUtils.split(permissionsString);
		if (tokens != null && tokens.length > 0) {
			return new LinkedHashSet<String>(Arrays.asList(tokens));
		}
		return null;
	}

	public static Set<Permission> resolvePermissions(Collection<String> permissionStrings) {
		Set<Permission> permissions = new LinkedHashSet<Permission>(permissionStrings.size());
		for (String permissionString : permissionStrings) {
			permissions.add(new WildcardPermission(permissionString));
		}
		return permissions;
	}
}