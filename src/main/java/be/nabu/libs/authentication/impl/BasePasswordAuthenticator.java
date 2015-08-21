package be.nabu.libs.authentication.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import be.nabu.libs.authentication.api.Authenticator;
import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.api.principals.BasicPrincipal;

abstract public class BasePasswordAuthenticator implements Authenticator {
	
	private Map<String, Properties> realms = new HashMap<String, Properties>();
	private boolean forceDefaultRealm;
	/**
	 * defaults to 1 day
	 */
	private long tokenTimeout = 24l*60l*60l*1000l;
	
	abstract protected InputStream getInput(String fileName) throws IOException;
	
	@Override
	public Token authenticate(final String realm, final Principal... credentials) {
		for (final Principal credential : credentials) {
			if (credential instanceof BasicPrincipal) {
				// if he is authenticated, return a valid token
				if (getRealm(realm).containsKey(credential.getName()) && getRealm(realm).get(credential.getName()).equals(((BasicPrincipal) credential).getPassword())) {
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

	private Properties getRealm(String name) {
		if (forceDefaultRealm) {
			name = "default";
		}
		if (!realms.containsKey(name)) {
			synchronized(realms) {
				if (!realms.containsKey(name)) {
					try {
						InputStream input = getInput("users." + cleanup(name) + ".properties");
						if (input == null) {
							throw new IllegalArgumentException("Non existent realm: " + name);
						}
						try {
							input = new BufferedInputStream(input);
							Properties properties = new Properties();
							properties.load(input);
							realms.put(name, properties);
						}
						finally {
							input.close();
						}
					}
					catch (IOException e) {
						throw new RuntimeException("Could not load realm " + name, e);
					}
				}
			}
		}
		return realms.get(name);
	}
	private String cleanup(String name) {
		return name.replaceAll("[^\\w._-]+", ".");
	}
	
	private class PasswordToken implements Token {
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

	public boolean isForceDefaultRealm() {
		return forceDefaultRealm;
	}

	public void setForceDefaultRealm(boolean forceDefaultRealm) {
		this.forceDefaultRealm = forceDefaultRealm;
	}

	public long getTokenTimeout() {
		return tokenTimeout;
	}

	public void setTokenTimeout(long tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
	}
}
