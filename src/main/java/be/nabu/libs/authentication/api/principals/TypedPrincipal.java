package be.nabu.libs.authentication.api.principals;

import java.io.Serializable;

public interface TypedPrincipal extends BasicPrincipal, Serializable {
	// the type of the principal (if any)
	public String getType();
	public String getSubType();
}
