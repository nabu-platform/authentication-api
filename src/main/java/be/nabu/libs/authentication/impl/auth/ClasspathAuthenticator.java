package be.nabu.libs.authentication.impl.auth;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathAuthenticator extends BasePasswordAuthenticator {
	
	@Override
	protected InputStream getInput(String fileName) throws IOException {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	}
	
}
