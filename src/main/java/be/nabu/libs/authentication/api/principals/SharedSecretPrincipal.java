package be.nabu.libs.authentication.api.principals;

import java.io.Serializable;
import java.security.Principal;

public interface SharedSecretPrincipal extends Principal, Serializable {
	public String getSecret();
}
