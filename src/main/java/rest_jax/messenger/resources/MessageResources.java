package rest_jax.messenger.resources;

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
import rest_jax.messenger.model.Message;
import rest_jax.messenger.service.MessageService;

@Path("/messages")
public class MessageResources {
	
	MessageService messageService = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if(filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart()>=0 && filterBean.getSize() >= 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message getOneMessage(@PathParam("messageId") long messageId){
		return messageService.getMessage(messageId);
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		return messageService.addMessage(message);
	} 
	
	@PUT
	@Path("/update/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
;		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/delete/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteMessage(@PathParam("messageId") long messageId) {
		return messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments") // This maps to CommentResource
    public CommentResource forwardToCommentResource(@PathParam("messageId") long messageId) {
        return new CommentResource();
    }
	
}
