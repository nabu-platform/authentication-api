package be.nabu.libs.authentication.api.principals;

public interface NTLMPrincipal extends BasicPrincipal {
	public String getDomain();
	public String getHostName();
}
