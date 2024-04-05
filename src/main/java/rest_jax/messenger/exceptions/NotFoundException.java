package rest_jax.messenger.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class NotFoundException extends WebApplicationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6908453825183858862L;

	public NotFoundException(String message) {
        super(Response.status(Status.NOT_FOUND).entity(message).build());
    }
}
