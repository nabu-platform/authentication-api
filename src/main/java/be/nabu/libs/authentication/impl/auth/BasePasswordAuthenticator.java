package be.nabu.libs.authentication.impl.auth;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.nabu.libs.authentication.api.Authenticator;
import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.api.principals.BasicPrincipal;
import be.nabu.libs.authentication.impl.BaseRealmHandler;

abstract public class BasePasswordAuthenticator extends BaseRealmHandler implements Authenticator {
	
	/**
	 * defaults to 1 day
	 */
	private long tokenTimeout = 24l*60l*60l*1000l;
	
	@Override
	public Token authenticate(final String realm, final Principal... credentials) {
		for (final Principal credential : credentials) {
			// can't log in as anonymous
			if (ANONYMOUS.equals(credential.getName())) {
				continue;
			}
			if (credential instanceof BasicPrincipal) {
				// if he is authenticated, return a valid token
				if (getRealm("users", realm).containsKey(credential.getName()) && getRealm("users", realm).get(credential.getName()).equals(((BasicPrincipal) credential).getPassword())) {
					return new PasswordToken(new Date(new Date().getTime() + tokenTimeout), realm, credential, credentials);
				}
			}
		}
		return null;
	}

	@Override
	public boolean isValid(Token token) {
		return token instanceof PasswordToken && equals(((PasswordToken) token).getAuthenticator());
	}

	public class PasswordToken implements Token {
		private final String realm;
		private final Principal credential;
		private final Principal[] credentials;
		private static final long serialVersionUID = 1L;
		private Date timeout;
		
		private PasswordToken(Date timeout, String realm, Principal credential, Principal[] credentials) {
			this.timeout = timeout;
			this.realm = realm;
			this.credential = credential;
			this.credentials = credentials;
		}
		
		@Override
		public String getName() {
			return credential.getName();
		}
		
		@Override
		public String getRealm() {
			return realm;
		}
		
		@Override
		public Date getValidUntil() {
			return timeout;
		}
		
		@Override
		public List<Principal> getCredentials() {
			return Arrays.asList(credentials);
		}
		
		public BasePasswordAuthenticator getAuthenticator() {
			return BasePasswordAuthenticator.this;
		}
	}

	public long getTokenTimeout() {
		return tokenTimeout;
	}

	public void setTokenTimeout(long tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
	}
}
