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
	public boolean hasRole(Token token, String...roles) {
		// check anonymous roles
		if (token == null) {
			for (String role : roles) {
				if (!anonymousRoles.contains(role)) {
					return false;
				}
			}
		}
		else {
			String user = token.getName();
			Properties realm = getRealm("roles", token.getRealm());
			for (String role : roles) {
				// if the realm doesn't know the role or the user is not in there, stop
				if (!defaultRoles.contains(role) && (!realm.containsKey(role) || Arrays.asList(realm.getProperty(role).split("[\\s,]+")).contains(user))) {
					return false;
				}
			}
		}
		return true;
	}

}
