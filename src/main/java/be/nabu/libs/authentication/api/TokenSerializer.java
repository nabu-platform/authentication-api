package be.nabu.libs.authentication.api;

import java.io.InputStream;
import java.io.OutputStream;

public interface TokenSerializer<T extends Token> {
	
	public void serialize(OutputStream output, T token);
	public T deserialize(InputStream input);
	
	// the name that this serializer can be identified by
	public String getName();
	
	// the type of tokens it can serialize
	public Class<T> getTokenType();
}
