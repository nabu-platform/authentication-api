package be.nabu.libs.authentication.api;

import javax.jws.WebParam;

public interface PermissionHandler {
	/**
	 * For a given context the user wants to do a specific action
	 * 
	 * For example in the case of REST services the context could be the full URI (with id in it to have a specific entity)
	 * And the method can be the http method (post, delete, get,...)
	 */
	public boolean hasPermission(@WebParam(name = "token") Token token, @WebParam(name = "context") String context, @WebParam(name = "action") String action);
}
