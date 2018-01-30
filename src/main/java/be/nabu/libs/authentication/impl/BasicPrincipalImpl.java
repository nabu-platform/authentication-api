package be.nabu.libs.authentication.impl;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.api.principals.BasicPrincipal;

public class BasicPrincipalImpl implements Token, BasicPrincipal {

	private static final long serialVersionUID = 1L;

	private String name, password, realm;

	public BasicPrincipalImpl() {
		// auto construct
	}
	
	public BasicPrincipalImpl(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public BasicPrincipalImpl(String name, String password, String realm) {
		this.name = name;
		this.password = password;
		this.realm = realm;
	}
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getRealm() {
		return realm;
	}

	@Override
	public Date getValidUntil() {
		return null;
	}

	@Override
	public List<Principal> getCredentials() {
		return null;
	}

}
