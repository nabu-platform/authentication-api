package be.nabu.libs.authentication.api;

public interface DeviceValidator {
	public boolean isAllowed(Token token, String deviceId, String deviceDescription);
}
