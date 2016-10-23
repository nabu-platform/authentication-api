package be.nabu.libs.authentication.impl;

import be.nabu.libs.authentication.api.principals.BasicPrincipal;

public class BasicPrincipalImpl implements BasicPrincipal {

	private static final long serialVersionUID = 1L;

	private String name, password;

	public BasicPrincipalImpl() {
		// auto construct
	}
	
	public BasicPrincipalImpl(String name, String password) {
		this.name = name;
		this.password = password;
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

}
