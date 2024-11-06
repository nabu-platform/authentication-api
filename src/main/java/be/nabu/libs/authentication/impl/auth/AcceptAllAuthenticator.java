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

package be.nabu.libs.authentication.impl.auth;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.nabu.libs.authentication.api.Authenticator;
import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.api.TokenValidator;

public class AcceptAllAuthenticator implements Authenticator, TokenValidator {

	@Override
	public Token authenticate(final String realm, final Principal...credentials) {
		return new Token() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getName() {
				return credentials == null || credentials.length == 0 ? "anonymous" : credentials[0].getName();
			}

			@Override
			public String getRealm() {
				return realm;
			}

			@Override
			public Date getValidUntil() {
				// a year
				return new Date(new Date().getTime() + 365l*24l*60l*60l*1000l);
			}

			@Override
			public List<Principal> getCredentials() {
				return Arrays.asList(credentials);
			}
		};
	}

	@Override
	public boolean isValid(Token token) {
		return true;
	}

}
