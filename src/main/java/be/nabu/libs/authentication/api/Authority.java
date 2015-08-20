package be.nabu.libs.authentication.api;

import java.security.cert.X509Certificate;

public interface Authority {
	/**
	 * The certificate for the private key used to create the signature
	 */
	public X509Certificate getCertificate();
	/**
	 * The entire chain used for trust 
	 */
	public X509Certificate [] getChain();
}
