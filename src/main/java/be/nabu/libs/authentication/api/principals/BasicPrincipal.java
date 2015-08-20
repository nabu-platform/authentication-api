package be.nabu.libs.authentication.api.principals;

import java.io.Serializable;
import java.security.Principal;

public interface BasicPrincipal extends Principal, Serializable {
	public String getPassword();
}
