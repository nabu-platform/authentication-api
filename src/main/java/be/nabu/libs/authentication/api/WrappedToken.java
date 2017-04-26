package be.nabu.libs.authentication.api;

public interface WrappedToken extends Token {
	public Token getOriginalToken();
}
