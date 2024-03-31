package rest_jax.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rest_jax.messenger.database.DatabaseClass;
import rest_jax.messenger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();	
	
	public MessageService() {
		messages.put((long) 1, new Message(1, "Hello World", "Naveen"));
		messages.put((long) 2, new Message(2, "Hello Java", "Sai"));
		messages.put((long) 3, new Message(3, "Hello Python", "Praveen"));
	}

	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <= 0 || !messages.containsKey(message.getId())) {
			return null;			
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
