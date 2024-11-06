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

package be.nabu.libs.authentication.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

abstract public class BaseRealmHandler {

	private Map<String, Map<String, Properties>> realms = new HashMap<String, Map<String, Properties>>();
	private boolean forceDefaultRealm;
	
	abstract protected InputStream getInput(String fileName) throws IOException;

	protected Properties getRealm(String group, String name) {
		if (forceDefaultRealm) {
			name = "default";
		}
		if (!realms.containsKey(name)) {
			synchronized(realms) {
				if (!realms.containsKey(name)) {
					try {
						InputStream input = getInput(group + "." + cleanup(name) + ".properties");
						if (input == null) {
							throw new IllegalArgumentException("Non existent realm: " + name);
						}
						try {
							input = new BufferedInputStream(input);
							Properties properties = new Properties();
							properties.load(input);
							if (!realms.containsKey(group)) {
								realms.put(group, new HashMap<String, Properties>());
							}
							realms.get(group).put(name, properties);
						}
						finally {
							input.close();
						}
					}
					catch (IOException e) {
						throw new RuntimeException("Could not load realm " + name, e);
					}
				}
			}
		}
		return realms.get(group).get(name);
	}
	
	public boolean isForceDefaultRealm() {
		return forceDefaultRealm;
	}

	public void setForceDefaultRealm(boolean forceDefaultRealm) {
		this.forceDefaultRealm = forceDefaultRealm;
	}
	
	private String cleanup(String name) {
		return name.replaceAll("[^\\w._-]+", ".");
	}
	
}
