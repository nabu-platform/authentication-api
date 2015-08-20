package be.nabu.libs.authentication.api;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * A token represents an authenticated principal
 * The principal "name" should be a unique identifier
 */
public interface Token extends Principal, Serializable {
	/**
	 * The realm this token is valid for
	 */
	public String getRealm();
	/**
	 * Until when the token is valid
	 */
	public Date getValidUntil();
	/**
	 * The original list of credentials
	 */
	public List<Principal> getCredentials();
}
