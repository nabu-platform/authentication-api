package be.nabu.libs.authentication.api;

import javax.jws.WebParam;
import javax.jws.WebResult;

public interface DeviceValidator {
	@WebResult(name = "isAllowed")
	public boolean isAllowed(@WebParam(name = "token") Token token, @WebParam(name = "device") Device device);
}
