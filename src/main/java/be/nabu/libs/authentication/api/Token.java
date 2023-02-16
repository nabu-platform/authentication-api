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
	 * A globally unique and _anonymous_ (GDPR and all that) id that identifies the identity. it is specifically not called user id as that has some human undertones
	 * This can be used to store security settings without knowing the exact context
	 * For example logging the owner, without knowing where he exists (CMS, SSO,...)
	 * This will almost always be a UUID, but to leave the door open to other generation schemes, it is defined as a string
	 * At higher levels we can enforce the uuid-ness of this id if relevant
	 */
	public default String getAuthenticationId() {
		return null;
	}
	
	/**
	 * The (logical) name of the service that has created the authentication
	 */
	public default String getAuthenticator() {
		return null;
	}
	/**
	 * One identity (authentication id) might have multiple tokens over time
	 * The token id is the identifier of that specific token, usually resolvable by the authenticator
	 */
	public default String getTokenId() {
		return null;
	}
	/**
	 * If this token is actually issued to someone else impersonating a user, this is the equivalent of the authentication id for that impersonator
	 */
	public default String getImpersonatorId() {
		return null;
	}
	/**
	 * The equivalent of the "name" for the impersonator
	 */
	public default String getImpersonator() {
		return null;
	}
	/**
	 * The realm of the impersonator, usually the same as the user
	 */
	public default String getImpersonatorRealm() {
		return null;
	}
}
