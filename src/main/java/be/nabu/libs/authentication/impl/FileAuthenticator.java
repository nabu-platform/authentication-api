package be.nabu.libs.authentication.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileAuthenticator extends BasePasswordAuthenticator {
	
	@Override
	protected InputStream getInput(String fileName) throws IOException {
		File file = new File(fileName);
		return file.exists() && file.isFile() ? new FileInputStream(file) : null;
	}
	
}
