package be.nabu.libs.authentication.impl.role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import be.nabu.libs.authentication.api.RoleHandler;
import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.impl.BaseRealmHandler;

abstract public class BaseRoleHandler extends BaseRealmHandler implements RoleHandler {

	private Set<String> anonymousRoles = new HashSet<String>(Arrays.asList(GUEST));
	private Set<String> defaultRoles = new HashSet<String>(Arrays.asList(USER));
	
	@Override
	public boolean hasRole(Token token, String role) {
		// check anonymous roles
		if (token == null) {
			if (!anonymousRoles.contains(role)) {
				return false;
			}
		}
		else {
			String user = token.getName();
			Properties realm = getRealm("roles", token.getRealm());
			// if it's a default role, you automatically have it
			if (defaultRoles.contains(role)) {
				return true;
			}
			// if the realm doesn't know the role or the user is not in there, stop
			if (!realm.containsKey(role) || !Arrays.asList(realm.getProperty(role).split("[\\s,]+")).contains(user)) {
				return false;
			}
		}
		return true;
	}

}
