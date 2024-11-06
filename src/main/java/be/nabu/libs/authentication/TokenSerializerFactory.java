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

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import be.nabu.libs.authentication.api.Token;
import be.nabu.libs.authentication.api.TokenSerializer;

public class TokenSerializerFactory {
	
	private static TokenSerializerFactory instance;
	
	public static TokenSerializerFactory getInstance() {
		if (instance == null) {
			synchronized(TokenSerializerFactory.class) {
				if (instance == null) {
					instance = new TokenSerializerFactory();
				}
			}
		}
		return instance;
	}
	
	private List<TokenSerializer<?>> serializers;
	
	public void addSerializer(TokenSerializer<?> serializer) {
		serializers.add(serializer);
	}
	public void removeSerializer(TokenSerializer<?> serializer) {
		// because the actual list is given to the multiple, it will automatically removed there as well
		serializers.remove(serializer);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Token> TokenSerializer<T> getSerializer(T token) {
		TokenSerializer<T> closest = null;
		for (TokenSerializer<?> serializer : getSerializers()) {
			if (serializer.getTokenType().isAssignableFrom(token.getClass())) {
				if (closest == null || closest.getTokenType().isAssignableFrom(serializer.getTokenType())) {
					closest = (TokenSerializer<T>) serializer;
				}
			}
		}
		return closest;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Token> TokenSerializer<T> getSerializer(String name) {
		for (TokenSerializer<?> serializer : getSerializers()) {
			if (name.equals(serializer.getName())) {
				return (TokenSerializer<T>) serializer;
			}
		}
		return null;
	}

	private List<TokenSerializer<?>> getSerializers() {
		if (serializers == null) {
			synchronized(this) {
				if (serializers == null) {
					List<TokenSerializer<?>> serializers = new ArrayList<TokenSerializer<?>>();
					for (TokenSerializer<?> serializer : ServiceLoader.load(TokenSerializer.class)) {
						serializers.add(serializer);
					}
					this.serializers = serializers;
				}
			}
		}
		return serializers;
	}
	@SuppressWarnings("unused")
	private void activate() {
		instance = this;
	}
	
	@SuppressWarnings("unused")
	private void deactivate() {
		instance = null;
	}
}
