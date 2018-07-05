package be.nabu.libs.authentication.impl;

import java.net.URI;
import java.security.Principal;

import be.nabu.libs.authentication.api.principals.BasicPrincipal;

public class AuthenticationUtils {
	
	public static Principal toPrincipal(final String username, final String password) {
		BasicPrincipal principal = null;
		if (username != null) {
			int index = username.indexOf('/');
			if (index < 0) {
				index = username.indexOf('\\');
			}
			if (index < 0) {
				principal = new BasicPrincipalImpl(username, password);
			}
			else {
				principal = new NTLMPrincipalImpl(username.substring(0, index), username.substring(index + 1), password, null);
			}
		}
		return principal;
	}
	
	public static BasicPrincipal toPrincipal(URI uri) {
		String username = null, password = null, domain = null;
		if (uri.getUserInfo() != null) {
			username = uri.getUserInfo();
			int index = username.indexOf(':');
			if (index > 0) {
				password = username.substring(index + 1);
				username = username.substring(0, index);
			}
			index = username.indexOf('/');
			if (index > 0) {
				domain = username.substring(0, index);
				username = username.substring(index + 1);
			}
		}
		if (username != null) {
			if (domain != null) {
				return new NTLMPrincipalImpl(domain, username, password, null);	
			}
			else {
				return new BasicPrincipalImpl(username, password);
			}
		}
		return null;
	}
}
