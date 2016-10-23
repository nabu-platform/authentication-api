package be.nabu.libs.authentication.impl;

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
}
