package be.nabu.libs.authentication.impl;

import be.nabu.libs.authentication.api.Device;

public class BasicPrincipalWithDeviceImpl extends BasicPrincipalImpl {
	private static final long serialVersionUID = 1L;
	
	private Device device;

	public BasicPrincipalWithDeviceImpl() {
		// auto construct
	}
	
	public BasicPrincipalWithDeviceImpl(String name, String password, Device device) {
		super(name, password);
		this.device = device;
	}
	
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

}
