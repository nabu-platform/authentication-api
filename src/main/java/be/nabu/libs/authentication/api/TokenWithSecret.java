package be.nabu.libs.authentication.api;

public interface TokenWithSecret extends Token {
	public String getSecret();
}
