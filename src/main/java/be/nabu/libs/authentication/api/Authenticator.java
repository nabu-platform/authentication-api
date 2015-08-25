package be.nabu.libs.authentication.api;

import java.security.Principal;

public interface Authenticator {
	public static final String ANONYMOUS = "anonymous";
	public Token authenticate(String realm, Principal...credentials);
	public boolean isValid(Token token);
}
