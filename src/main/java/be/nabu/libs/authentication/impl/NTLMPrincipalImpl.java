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
