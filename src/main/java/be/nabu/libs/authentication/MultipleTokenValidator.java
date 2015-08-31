package be.nabu.libs.authentication;

import java.util.List;

import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.api.TokenValidator;

public class MultipleTokenValidator implements TokenValidator {
	
	private List<TokenValidator> tokenValidators;

	public MultipleTokenValidator(List<TokenValidator> tokenValidators) {
		this.tokenValidators = tokenValidators;
	}

	@Override
	public boolean isValid(Token token) {
		boolean isValid = false;
		for (TokenValidator authenticator : tokenValidators) {
			isValid = authenticator.isValid(token);
			if (isValid) {
				break;
			}
		}
		return isValid;
	}
}
