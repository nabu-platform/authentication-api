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
