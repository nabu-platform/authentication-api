package be.nabu.libs.authentication.impl;

import be.nabu.libs.authentication.api.Permission;

public class PermissionImpl implements Permission {
	private String action, context;
	
	public PermissionImpl() {
		// auto
	}
	
	public PermissionImpl(String action, String context) {
		this.action = action;
		this.context = context;
	}

	@Override
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
}
