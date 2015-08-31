package be.nabu.libs.authentication.api;

import java.security.Principal;

import javax.jws.WebParam;

public interface Authenticator {
	public static final String ANONYMOUS = "anonymous";
	public Token authenticate(@WebParam(name = "realm") String realm, @WebParam(name = "credentials") Principal...credentials);
}
