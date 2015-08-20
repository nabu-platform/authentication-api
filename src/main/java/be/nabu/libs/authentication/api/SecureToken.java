package be.nabu.libs.authentication.api;

public interface SecureToken extends Token {
	/**
	 * A random salt
	 */
	public String getSalt();
	/**
	 * The token must sign the following string values (separated by a ":")
	 * realm:name:validUntil:salt
	 * Note that the "validUntil" date should be in the xml dateTime format:
	 * yyyy-MM-dd'T'HH:mm:ss.SSS
	 */
	public byte [] getSignature();
	/**
	 * Get the authority that issued this token
	 */
	public Authority getAuthority();
}
