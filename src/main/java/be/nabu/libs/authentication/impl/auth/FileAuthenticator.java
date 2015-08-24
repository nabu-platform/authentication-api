package be.nabu.libs.authentication.impl.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileAuthenticator extends BasePasswordAuthenticator {
	
	private File root;

	public FileAuthenticator() {}
	
	public FileAuthenticator(File root) {
		this.root = root;
	}
	
	@Override
	protected InputStream getInput(String fileName) throws IOException {
		File file = root == null ? new File(fileName) : new File(root, fileName);
		return file.exists() && file.isFile() ? new FileInputStream(file) : null;
	}
	
}
