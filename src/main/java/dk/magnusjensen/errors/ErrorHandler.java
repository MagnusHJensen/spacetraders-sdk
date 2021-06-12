package dk.magnusjensen.errors;

import com.fasterxml.jackson.databind.JsonNode;

public class ErrorHandler {

	public static void handleError(JsonNode node) throws Exception {

		switch (node.get("code").asInt()) {
			case 40101: {
				throw new InvalidTokenException(40101);
			}
			case 404: {
				throw new ObjectNotFoundException(404);
			}
		}
	}
}
