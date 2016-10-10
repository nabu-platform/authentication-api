package be.nabu.libs.authentication.api.principals;

import java.io.Serializable;
import java.security.Principal;

import be.nabu.libs.authentication.api.Device;

public interface DevicePrincipal extends Principal, Serializable {
	public Device getDevice();
}
