package rest_jax.messenger.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class BadRequestException extends WebApplicationException {
    public BadRequestException(String message) {
        super(Response.status(Status.BAD_REQUEST).entity(message).build());
    }
}
