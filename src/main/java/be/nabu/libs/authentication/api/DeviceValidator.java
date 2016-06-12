package be.nabu.libs.authentication.api;

import javax.jws.WebParam;

public interface DeviceValidator {
	public String newDeviceId(@WebParam(name = "token") Token token, @WebParam(name = "remoteIp") String remoteIp, @WebParam(name = "deviceDescription") String deviceDescription);
	public boolean isAllowed(@WebParam(name = "token") Token token, @WebParam(name = "remoteIp") String remoteIp, @WebParam(name = "deviceId") String deviceId);
}
