/*
* Copyright (C) 2015 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
