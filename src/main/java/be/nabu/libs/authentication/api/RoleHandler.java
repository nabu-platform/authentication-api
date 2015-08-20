package be.nabu.libs.authentication.api;

public interface RoleHandler {
	public boolean hasRole(Token token, String...roles);
}
