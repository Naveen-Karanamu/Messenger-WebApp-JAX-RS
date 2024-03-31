package rest_jax.messenger.service;

import java.util.ArrayList;
import java.util.List;

import rest_jax.messenger.model.Message;

public class MessageService {

	public List<Message> getAllMessages(){
		Message m1 = new Message(1, "hello world", "Naveen");
		Message m2 = new Message(2, "Welcome", "Sai");
		Message m3 = new Message(3, "Lets Begin", "Praveen");
		
		List<Message> list = new ArrayList<>();
		
		list.add(m1);
		list.add(m2);
		list.add(m3);
		
		return list;
	}
}
