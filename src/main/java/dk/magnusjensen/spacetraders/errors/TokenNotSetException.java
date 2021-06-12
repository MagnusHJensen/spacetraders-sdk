package dk.magnusjensen.spacetraders.errors;

public class TokenNotSetException extends Exception {

	public TokenNotSetException() {
		super("The API token has not been set!");
	}
}
