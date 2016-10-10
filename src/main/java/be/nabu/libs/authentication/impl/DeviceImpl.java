package be.nabu.libs.authentication.impl;

import be.nabu.libs.authentication.api.Device;

public class DeviceImpl implements Device {

	private String deviceId, deviceDescription, remoteHost;

	public DeviceImpl(String deviceId, String deviceDescription, String remoteHost) {
		this.deviceId = deviceId;
		this.deviceDescription = deviceDescription;
		this.remoteHost = remoteHost;
	}
	
	public DeviceImpl() {
		// auto
	}
	
	@Override
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String getDeviceDescription() {
		return deviceDescription;
	}
	public void setDeviceDescription(String deviceDescription) {
		this.deviceDescription = deviceDescription;
	}

	@Override
	public String getRemoteHost() {
		return remoteHost;
	}
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
}
