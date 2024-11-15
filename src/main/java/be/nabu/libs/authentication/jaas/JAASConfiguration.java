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

package be.nabu.libs.authentication.jaas;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;

public class JAASConfiguration extends Configuration {

	private static boolean registered = false;
	private static JAASConfiguration instance;
	
	static {
		instance = new JAASConfiguration(Configuration.getConfiguration());
	}
	
	public static void register() {
		if (!registered) {
			synchronized(JAASConfiguration.class) {
				if (!registered) {
					Configuration.setConfiguration(instance);
					registered = true;
				}
			}
		}
	}
	
	public static JAASConfiguration getInstance() {
		return instance;
	}
	
	private Configuration parent;
	private Map<String, AppConfigurationEntry[]> entries = new HashMap<String, AppConfigurationEntry[]>();

	private JAASConfiguration(Configuration parent) {
		this.parent = parent;
	}
	
	@Override
	public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
		if (entries.containsKey(name)){
			return entries.get(name);
		}
		else if (parent != null) {
			return parent.getAppConfigurationEntry(name);
		}
		return null;
	}

	public void configure(String name, AppConfigurationEntry...entries) {
		this.entries.put(name, entries);
	}
	
	public void remove(String name) {
		this.entries.remove(name);
	}
	
	public static AppConfigurationEntry newKerberosEntry(Map<String, String> options) {
		return new AppConfigurationEntry("com.sun.security.auth.module.Krb5LoginModule", LoginModuleControlFlag.REQUIRED, options);
	}
}
