package be.nabu.libs.authentication.api.principals;

import java.io.Serializable;
import java.security.Principal;

public interface TypedPrincipal extends Principal, Serializable {
	// the type of the principal (if any)
	public String getType();
}
