package edu.sabanciuniv.it526;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import edu.sabanciuniv.it526.entity.Conversation;
import edu.sabanciuniv.it526.entity.User;
import edu.sabanciuniv.it526.repository.ConversationRepositoy;
import edu.sabanciuniv.it526.repository.UserRepositoy;
import java.time.*;


@RestController
public class ConversationController {
	
	@Autowired
	private UserRepositoy userRepository;
	
	@Autowired
	private ConversationRepositoy conversationRepository;
	
	
	@GetMapping("/createconv")
	public Conversation newConversation(@RequestParam(name = "receiver") long receiverId, @RequestParam(name = "sender") long senderId )
	{	
		
		
		User receiver = userRepository.findUsersById(Long.valueOf(receiverId)).get(0);
		
		User sender = userRepository.findUsersById(Long.valueOf(senderId)).get(0);
		
		List<User> users = new ArrayList<User>();
		
		users.add(receiver);
		users.add(sender);
		
		Conversation newconv = new Conversation(users, LocalDateTime.now());
		
		
		return conversationRepository.save(newconv);
	}
	
}
