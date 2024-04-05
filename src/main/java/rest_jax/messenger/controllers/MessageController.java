package rest_jax.messenger.controllers;

import java.util.List;

import jakarta.ws.rs.BeanParam;
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
import rest_jax.messenger.model.Message;
import rest_jax.messenger.service.MessageService;

@Path("/messages")
public class MessageController {
    
    private final MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages(@BeanParam MessageFilterBean filterBean) {
        try {
            if (filterBean.getYear() > 0) {
                List<Message> messages = messageService.getAllMessagesForYear(filterBean.getYear());
                return Response.ok().entity(messages).build();
            } else if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0) {
                List<Message> messages = messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
                return Response.ok().entity(messages).build();
            } else {
                List<Message> messages = messageService.getAllMessages();
                return Response.ok().entity(messages).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error occurred while fetching messages: " + e.getMessage())
                           .build();
        }
    }
    
    @GET
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneMessage(@PathParam("messageId") long messageId){
        try {
            Message message = messageService.getMessage(messageId);
            if (message != null) {
                return Response.ok().entity(message).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Message not found with id: " + messageId)
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error occurred while fetching the message: " + e.getMessage())
                           .build();
        }
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMessage(Message message) {
        try {
            Message addedMessage = messageService.addMessage(message);
            return Response.status(Response.Status.CREATED).entity(addedMessage).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Error occurred while adding message: " + e.getMessage())
                           .build();
        }
    } 
    
    @PUT
    @Path("/update/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMessage(@PathParam("messageId") long messageId, Message message) {
        try {
            Message existingMessage = messageService.getMessage(messageId);
            if (existingMessage == null) {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Message not found with id: " + messageId)
                               .build();
            }
            message.setId(messageId);
            Message updatedMessage = messageService.updateMessage(message);
            return Response.ok().entity(updatedMessage).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Error occurred while updating message: " + e.getMessage())
                           .build();
        }
    }

    
    @DELETE
    @Path("/delete/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMessage(@PathParam("messageId") long messageId) {
        try {
            Message deletedMessage = messageService.removeMessage(messageId);
            if (deletedMessage != null) {
                return Response.ok().entity(deletedMessage).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Message not found with id: " + messageId)
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error occurred while deleting message: " + e.getMessage())
                           .build();
        }
    }
    
    @Path("/{messageId}/comments") // This maps to CommentResource
    public CommentController forwardToCommentResource(@PathParam("messageId") long messageId) {
        return new CommentController();
    }
}
