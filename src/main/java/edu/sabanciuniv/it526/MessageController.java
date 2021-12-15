package edu.sabanciuniv.it526;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import edu.sabanciuniv.it526.entity.Message;
import edu.sabanciuniv.it526.repository.ConversationRepositoy;
import edu.sabanciuniv.it526.repository.MessageRepository;
import edu.sabanciuniv.it526.repository.UserRepositoy;
import java.time.*;
import java.util.*;

@RestController
public class MessageController {
	
	@Autowired
	private UserRepositoy userRepository;
	
	@Autowired
	private MessageRepository messageRespository;
	
	@Autowired
	private ConversationRepositoy conversationRepository;
	
	
	@GetMapping("/saveMessage")
	public Message saveMessage(@RequestParam(name = "text") String text, @RequestParam(name = "sender") String senderId,
			@RequestParam(name = "conv") int convId)
	{	
		
		
		long sender = Long.valueOf(senderId);
		
		Message message = new Message();
		
		
		message.setSender(userRepository.findUsersById(sender).get(0));
		message.setText(text);
		message.setCreatedAt(LocalDateTime.now());
		message.setConversation(conversationRepository.getConversationById(convId));
		
		return messageRespository.save(message);
	}
	
	
	
}
