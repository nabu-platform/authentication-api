package be.nabu.libs.authentication.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.security.Principal;

import be.nabu.libs.authentication.api.principals.BasicPrincipal;

public class AuthenticationUtils {
	
	public static Principal toPrincipal(final String username, final String password) {
		BasicPrincipal principal = null;
		if (username != null) {
			int index = username.indexOf('/');
			if (index < 0) {
				index = username.indexOf('\\');
			}
			if (index < 0) {
				principal = new BasicPrincipalImpl(username, password);
			}
			else {
				principal = new NTLMPrincipalImpl(username.substring(0, index), username.substring(index + 1), password, null);
			}
		}
		return principal;
	}
	
	public static BasicPrincipal toPrincipal(URI uri) {
		String username = null, password = null, domain = null;
		if (uri.getUserInfo() != null) {
			username = uri.getUserInfo();
			int index = username.indexOf(':');
			if (index > 0) {
				password = username.substring(index + 1);
				username = username.substring(0, index);
			}
			index = username.indexOf('/');
			if (index > 0) {
				domain = username.substring(0, index);
				username = username.substring(index + 1);
			}
		}
		if (username != null) {
			username = decodeURL(username);
			if (password != null) {
				password = decodeURL(password);
			}
			if (domain != null) {
				domain = decodeURL(domain);
				return new NTLMPrincipalImpl(domain, username, password, null);	
			}
			else {
				return new BasicPrincipalImpl(username, password);
			}
		}
		return null;
	}
	
	private static String decodeURL(String uri) {
		try {
			// if you run this code:
			// System.out.println(URLDecoder.decode("abc%00de", "UTF-8"));
			// it will output "abc"
			if (uri.contains("%00")) {
				throw new RuntimeException("Unsafe string for decoding");
			}
			return URLDecoder.decode(uri, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}
