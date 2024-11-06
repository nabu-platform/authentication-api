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

import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.api.principals.BasicPrincipal;
import be.nabu.libs.authentication.api.principals.TypedPrincipal;

public class BasicPrincipalImpl implements Token, BasicPrincipal, TypedPrincipal {

	private static final long serialVersionUID = 1L;

	private String name, password, realm, type, subType;

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

	@Override
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}

}
