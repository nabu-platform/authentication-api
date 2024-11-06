/*
* Copyright (C) 2015 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
	private String authenticationId;
	
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
	
	// needed for auto-reconstruct
	public void setValidUntil(Date date) {
		// do nothing
	}
	public void setCredentials(List<Principal> credentials) {
		// do nothing
	}

	@Override
	public String getAuthenticationId() {
		return authenticationId;
	}
	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}
}
