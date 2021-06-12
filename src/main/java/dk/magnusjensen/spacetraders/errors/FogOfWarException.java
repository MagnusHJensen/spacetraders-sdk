package dk.magnusjensen.spacetraders_sdk.errors;

public class FogOfWarException extends Exception {
	public FogOfWarException(int code) {
		super("[Code: " + code + "] Do you have a ship here? Fog of war is a thing.");
	}
}
