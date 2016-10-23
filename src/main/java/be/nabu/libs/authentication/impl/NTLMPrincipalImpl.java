package be.nabu.libs.authentication.impl;

import be.nabu.libs.authentication.api.principals.NTLMPrincipal;

public class NTLMPrincipalImpl extends BasicPrincipalImpl implements NTLMPrincipal {

	private static final long serialVersionUID = 1L;
	
	private String domain;
	private String hostname;

	public NTLMPrincipalImpl(String domain, String name, String password, String hostname) {
		super(name, password);
		this.domain = domain;
		this.hostname = hostname;
	}
	
	@Override
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String getHostName() {
		return hostname;
	}
	public void setHostName(String hostname) {
		this.hostname = hostname;
	}
}
