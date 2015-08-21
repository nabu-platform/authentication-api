package be.nabu.libs.authentication.impl;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.nabu.libs.authentication.api.Authenticator;
import be.nabu.libs.authentication.api.Token;

public class AcceptAllAuthenticator implements Authenticator {

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
