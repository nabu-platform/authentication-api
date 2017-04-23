package be.nabu.libs.authentication.impl;

import java.util.Date;

import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.api.TokenValidator;

public class TimeoutTokenValidator implements TokenValidator {

	@Override
	public boolean isValid(Token token) {
		return token.getValidUntil() == null || token.getValidUntil().after(new Date());
	}

}
