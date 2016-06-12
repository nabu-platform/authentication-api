package be.nabu.libs.authentication.api;

import javax.jws.WebParam;
import javax.jws.WebResult;

public interface DeviceValidator {
	@WebResult(name = "deviceId")
	public String newDeviceId(@WebParam(name = "token") Token token, @WebParam(name = "remoteIp") String remoteIp, @WebParam(name = "deviceDescription") String deviceDescription);
	@WebResult(name = "isAllowed")
	public Boolean isAllowed(@WebParam(name = "token") Token token, @WebParam(name = "remoteIp") String remoteIp, @WebParam(name = "deviceId") String deviceId);
}
