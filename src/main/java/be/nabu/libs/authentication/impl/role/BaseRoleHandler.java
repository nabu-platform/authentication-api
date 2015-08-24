package be.nabu.libs.authentication.impl.role;

import java.util.Arrays;
import java.util.Properties;

import be.nabu.libs.authentication.api.RoleHandler;
import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.impl.BaseRealmHandler;

abstract public class BaseRoleHandler extends BaseRealmHandler implements RoleHandler {

	@Override
	public boolean hasRole(Token token, String...roles) {
		String user = token == null ? "anonymous" : token.getName();
		Properties realm = getRealm("roles", token.getRealm());
		for (String role : roles) {
			// if the realm doesn't know the role or the user is not in there, stop
			if (!realm.containsKey(role) || Arrays.asList(realm.getProperty(role).split("[\\s,]+")).contains(user)) {
				return false;
			}
		}
		return true;
	}

}
