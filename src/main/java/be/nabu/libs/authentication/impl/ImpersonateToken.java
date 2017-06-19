package be.nabu.libs.authentication.impl;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import be.nabu.libs.authentication.api.RefreshableToken;
import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.api.WrappedToken;

public class ImpersonateToken implements RefreshableToken, WrappedToken {

	private static final long serialVersionUID = 1L;
	
	private Token originalToken;
	private String realm;
	private String name;
	
	public ImpersonateToken() {
		// auto
	}
	
	public ImpersonateToken(Token originalToken, String realm, String name) {
		this.originalToken = originalToken;
		this.realm = realm;
		this.name = name;
		if (realm == null && originalToken != null) {
			this.realm = originalToken.getRealm();
		}
	}

	@Override
	public Token getOriginalToken() {
		return originalToken;
	}
	public void setOriginalToken(Token originalToken) {
		this.originalToken = originalToken;
	}
	
	@Override
	public String getRealm() {
		return realm;
	}
	public void setRealm(String realm) {
		this.realm = realm;
	}

	@Override
	public Date getValidUntil() {
		return originalToken == null ? null : originalToken.getValidUntil();
	}
	
	@Override
	public List<Principal> getCredentials() {
		return originalToken == null ? null : originalToken.getCredentials();
	}
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Token refresh() {
		if (originalToken instanceof RefreshableToken) {
			Token refresh = ((RefreshableToken) originalToken).refresh();
			return refresh == null ? null : new ImpersonateToken(refresh, realm, name);
		}
		return null;
	}
}
