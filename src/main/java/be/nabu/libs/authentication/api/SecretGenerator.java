package be.nabu.libs.authentication.api;

import javax.jws.WebParam;
import javax.jws.WebResult;

public interface SecretGenerator {
	@WebResult(name = "secret")
	public String generate(@WebParam(name = "token") Token token, @WebParam(name = "device") Device device);
}
