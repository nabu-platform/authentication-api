package be.nabu.libs.authentication.impl.role;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathRoleHandler extends BaseRoleHandler {
	
	@Override
	protected InputStream getInput(String fileName) throws IOException {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	}
	
}
