package be.nabu.libs.authentication.impl.role;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileRoleHandler extends BaseRoleHandler {
	
	private File root;

	public FileRoleHandler() {}
	
	public FileRoleHandler(File root) {
		this.root = root;
	}
	
	@Override
	protected InputStream getInput(String fileName) throws IOException {
		File file = root == null ? new File(fileName) : new File(root, fileName);
		return file.exists() && file.isFile() ? new FileInputStream(file) : null;
	}
	
}
