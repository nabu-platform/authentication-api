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

package be.nabu.libs.authentication;

import java.security.Principal;
import java.util.List;

import be.nabu.libs.authentication.api.Authenticator;
import be.nabu.libs.authentication.api.Token;

public class MultipleAuthenticator implements Authenticator {
	
	private List<Authenticator> authenticators;

	public MultipleAuthenticator(List<Authenticator> authenticators) {
		this.authenticators = authenticators;
	}

	@Override
	public Token authenticate(String realm, Principal... credentials) {
		for (Authenticator authenticator : authenticators) {
			Token token = authenticator.authenticate(realm, credentials);
			if (token != null) {
				return token;
			}
		}
		return null;
	}

	
}
