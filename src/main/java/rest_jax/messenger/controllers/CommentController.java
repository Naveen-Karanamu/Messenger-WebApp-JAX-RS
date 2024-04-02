package rest_jax.messenger.controllers;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rest_jax.messenger.model.Comment;
import rest_jax.messenger.service.CommentService;
import rest_jax.messenger.validators.CommentValidator;
import rest_jax.messenger.validators.InputValidator;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentController {
    
    private final CommentService commentService = new CommentService();
    
    @GET
    @Path("/allComment")
    public Response getAllComments(@PathParam("messageId") long messageId) {
        try {
            List<Comment> comments = commentService.getAllComments(messageId);
            return Response.ok().entity(comments).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error occurred while fetching comments: " + e.getMessage())
                           .build();
        }
    }
    
    @GET
    @Path("/{commentId}")
    public Response getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
    	 if (!InputValidator.isValidNumber(commentId)) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid commentId format: " + commentId)
                           .build();
        }

    	try {
            Comment comment = commentService.getComment(messageId, commentId);
            if (comment != null) {
                return Response.ok().entity(comment).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Comment not found with id: " + commentId)
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error occurred while fetching the comment: " + e.getMessage())
                           .build();
        }
    }
    
    @POST
    @Path("/addComment")
    public Response addComment(@PathParam("messageId") long messageId, Comment comment) {
        try {
            CommentValidator.validateComment(comment);
            Comment addedComment = commentService.addComment(messageId, comment);
            return Response.status(Response.Status.CREATED).entity(addedComment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Validation failed: " + e.getMessage())
                           .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error occurred while adding comment: " + e.getMessage())
                           .build();
        }
    }
    
    @PUT
    @Path("/{commentId}")
    public Response updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long id, Comment comment) {
    	if (!InputValidator.isValidNumber(id)) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid commentId format: " + id)
                           .build();
        }
    	
    	try {
            Comment existingComment = commentService.getComment(messageId, id);
            if (existingComment == null) {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Comment not found with id: " + id)
                               .build();
            }
            comment.setId(id);
            Comment updatedComment = commentService.updateComment(messageId, comment);
            return Response.ok().entity(updatedComment).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Error occurred while updating comment: " + e.getMessage())
                           .build();
        }
    }
    
    @DELETE
    @Path("/{commentId}")
    public Response deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
    	if (!InputValidator.isValidNumber(commentId)) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid commentId format: " + commentId)
                           .build();
        }
    	
    	try {
            Comment deletedComment = commentService.removeComment(messageId, commentId);
            if (deletedComment != null) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Comment not found with id: " + commentId)
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error occurred while deleting comment: " + e.getMessage())
                           .build();
        }
    }
}
