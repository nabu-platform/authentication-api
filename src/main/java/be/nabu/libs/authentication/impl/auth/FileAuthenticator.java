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
