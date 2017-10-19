package be.nabu.libs.authentication.api;

import javax.jws.WebParam;

// this is more or less like the permission handler but without a context
// it allows you to statically check if there are cases where a user might be permitted to perform the action
// at runtime however, depending on the particular context, the permission might not be granted by the permission handler
public interface PotentialPermissionHandler {
	public boolean hasPotentialPermission(@WebParam(name = "token") Token token, @WebParam(name = "action") String action);
}
