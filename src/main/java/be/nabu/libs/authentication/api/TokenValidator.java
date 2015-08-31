package be.nabu.libs.authentication.api;

import javax.jws.WebParam;

public interface TokenValidator {
	public boolean isValid(@WebParam(name = "token") Token token);
}
