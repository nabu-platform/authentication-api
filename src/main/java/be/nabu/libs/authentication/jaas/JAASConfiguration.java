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
