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
	/**
	 * A globally unique and _anonymous_ (GDPR and all that) id
	 * This can be used to store security settings without knowing the exact context
	 * For example logging the owner, without knowing where he exists (CMS, SSO,...)
	 * This will almost always be a UUID, but to leave the door open to other generation schemes, it is defined as a string
	 * At higher levels we can enforce the uuid-ness of this id if relevant
	 */
	public default String getId() {
		return null;
	}
}
