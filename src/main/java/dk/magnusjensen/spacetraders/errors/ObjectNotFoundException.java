package dk.magnusjensen.spacetraders_sdk.errors;

public class ObjectNotFoundException extends Exception {
	public ObjectNotFoundException(int code) {
		super("[Code: " + code + "] The requested object was not found!");
	}
}
