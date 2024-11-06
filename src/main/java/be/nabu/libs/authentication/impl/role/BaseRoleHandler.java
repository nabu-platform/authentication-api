/*
* Copyright (C) 2015 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
