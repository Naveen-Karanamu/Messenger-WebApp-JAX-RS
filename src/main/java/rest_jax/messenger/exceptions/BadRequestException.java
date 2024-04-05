package rest_jax.messenger.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class BadRequestException extends WebApplicationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7450311590957764075L;

	public BadRequestException(String message) {
        super(Response.status(Status.BAD_REQUEST).entity(message).build());
    }
}
