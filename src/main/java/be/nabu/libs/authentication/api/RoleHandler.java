package be.nabu.libs.authentication.api;

public interface RoleHandler {
	public static final String GUEST = "guest";
	public static final String USER = "user";
	public boolean hasRole(Token token, String...roles);
}
