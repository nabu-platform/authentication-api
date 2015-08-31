package be.nabu.libs.authentication.api;

import javax.jws.WebParam;

public interface RoleHandler {
	public static final String GUEST = "guest";
	public static final String USER = "user";
	public boolean hasRole(@WebParam(name = "token") Token token, @WebParam(name = "roles") String...roles);
}
