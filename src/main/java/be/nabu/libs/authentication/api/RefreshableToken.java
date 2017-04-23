package be.nabu.libs.authentication.api;

public interface RefreshableToken extends Token {
	public Token refresh();
}
