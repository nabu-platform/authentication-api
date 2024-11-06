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

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import be.nabu.libs.authentication.api.Authenticator;

public class AuthenticatorFactory {
	
	private static AuthenticatorFactory instance;
	
	public static AuthenticatorFactory getInstance() {
		if (instance == null) {
			synchronized(AuthenticatorFactory.class) {
				if (instance == null) {
					instance = new AuthenticatorFactory();
				}
			}
		}
		return instance;
	}
	
	private List<Authenticator> authenticators;
	private Authenticator authenticator;
	
	public void addAuthenticator(Authenticator authenticator) {
		authenticators.add(authenticator);
	}
	public void removeAuthenticator(Authenticator authenticator) {
		// because the actual list is given to the multiple, it will automatically removed there as well
		authenticators.remove(authenticator);
	}
	
	public Authenticator getAuthenticator() {
		if (authenticator == null) {
			synchronized(this) {
				if (authenticator == null) {
					authenticator = new MultipleAuthenticator(getAuthenticators());
				}
			}
		}
		return authenticator;
	}

	private List<Authenticator> getAuthenticators() {
		if (authenticators == null) {
			synchronized(this) {
				if (authenticators == null) {
					List<Authenticator> authenticators = new ArrayList<Authenticator>();
					for (Authenticator authenticator : ServiceLoader.load(Authenticator.class)) {
						authenticators.add(authenticator);
					}
					this.authenticators = authenticators;
				}
			}
		}
		return authenticators;
	}
	@SuppressWarnings("unused")
	private void activate() {
		instance = this;
	}
	@SuppressWarnings("unused")
	private void deactivate() {
		instance = null;
	}
}
