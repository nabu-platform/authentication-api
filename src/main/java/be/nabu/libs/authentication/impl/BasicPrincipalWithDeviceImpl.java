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

import be.nabu.libs.authentication.api.Device;
import be.nabu.libs.authentication.api.principals.DevicePrincipal;

public class BasicPrincipalWithDeviceImpl extends BasicPrincipalImpl implements DevicePrincipal {
	private static final long serialVersionUID = 1L;
	
	private Device device;

	public BasicPrincipalWithDeviceImpl() {
		// auto construct
	}
	
	public BasicPrincipalWithDeviceImpl(String name, String password, Device device) {
		super(name, password);
		this.device = device;
	}
	
	@Override
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

}
