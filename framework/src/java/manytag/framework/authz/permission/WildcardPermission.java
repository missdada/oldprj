package manytag.framework.authz.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import manytag.framework.authz.Permission;
import manytag.framework.util.CollectionUtils;

public class WildcardPermission implements Permission, Serializable {
	private static final long serialVersionUID = 5876947765049086064L;

	protected static final String WILDCARD_TOKEN = "*";
	protected static final String PART_DIVIDER_TOKEN = ":";
	protected static final String SUBPART_DIVIDER_TOKEN = ",";
	protected static final boolean DEFAULT_CASE_SENSITIVE = false;

	private List<Set<String>> parts;

	protected WildcardPermission() {
	}

	/**
	 * 构造函数
	 * 
	 * @param wildcardString 权限字符串
	 */
	public WildcardPermission(String wildcardString) {
		this(wildcardString, DEFAULT_CASE_SENSITIVE);
	}

	/**
	 * 构造函数
	 * 
	 * @param wildcardString 权限字符串
	 * @param caseSensitive 大小写是否敏感,缺省false 不敏感
	 */
	public WildcardPermission(String wildcardString, boolean caseSensitive) {
		setParts(wildcardString, caseSensitive);
	}

	protected void setParts(String wildcardString) {
		setParts(wildcardString, DEFAULT_CASE_SENSITIVE);
	}

	protected void setParts(String wildcardString, boolean caseSensitive) {
		if (wildcardString == null || wildcardString.trim().length() == 0) {
			throw new IllegalArgumentException("非法权限字符格式，权限字符不能为空.");
		}

		wildcardString = wildcardString.trim();

		List<String> parts = CollectionUtils.asList(wildcardString.split(PART_DIVIDER_TOKEN));

		this.parts = new ArrayList<Set<String>>();
		for (String part : parts) {
			Set<String> subparts = CollectionUtils.asSet(part.split(SUBPART_DIVIDER_TOKEN));
			if (!caseSensitive) {
				subparts = lowercase(subparts);
			}
			if (subparts.isEmpty()) {
				throw new IllegalArgumentException("权限字符格式错误，请检查格式是否正确.");
			}
			this.parts.add(subparts);
		}

		if (this.parts.isEmpty()) {
			throw new IllegalArgumentException("权限字符格式错误，请检查格式是否正确.");
		}
	}

	private Set<String> lowercase(Set<String> subparts) {
		Set<String> lowerCasedSubparts = new LinkedHashSet<String>(subparts.size());
		for (String subpart : subparts) {
			lowerCasedSubparts.add(subpart.toLowerCase());
		}
		return lowerCasedSubparts;
	}

	protected List<Set<String>> getParts() {
		return this.parts;
	}

	public boolean implies(Permission p) {
		if (!(p instanceof WildcardPermission)) {
			return false;
		}

		WildcardPermission wp = (WildcardPermission) p;

		List<Set<String>> otherParts = wp.getParts();

		int i = 0;
		for (Set<String> otherPart : otherParts) {
			if (getParts().size() - 1 < i) {
				return true;
			} else {
				Set<String> part = getParts().get(i);
				if (!part.contains(WILDCARD_TOKEN) && !part.containsAll(otherPart)) {
					return false;
				}
				i++;
			}
		}

		for (; i < getParts().size(); i++) {
			Set<String> part = getParts().get(i);
			if (!part.contains(WILDCARD_TOKEN)) {
				return false;
			}
		}

		return true;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		for (Set<String> part : parts) {
			if (buffer.length() > 0) {
				buffer.append(":");
			}
			buffer.append(part);
		}
		return buffer.toString();
	}

	public boolean equals(Object o) {
		if (o instanceof WildcardPermission) {
			WildcardPermission wp = (WildcardPermission) o;
			return parts.equals(wp.parts);
		}
		return false;
	}

	public int hashCode() {
		return parts.hashCode();
	}
}