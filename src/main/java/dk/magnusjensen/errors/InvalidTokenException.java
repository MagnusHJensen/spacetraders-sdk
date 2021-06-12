package dk.magnusjensen.errors;

public class InvalidTokenException extends Exception {
	public InvalidTokenException(int errorCode) {
		super( "[Code: " + errorCode + "] Token was invalid or missing from the request.");
	}
}
