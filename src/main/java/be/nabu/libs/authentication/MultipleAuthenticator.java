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
