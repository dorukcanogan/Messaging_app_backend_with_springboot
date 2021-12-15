package edu.sabanciuniv.it526;

import edu.sabanciuniv.it526.entity.*;
import edu.sabanciuniv.it526.repository.ConversationRepositoy;
import edu.sabanciuniv.it526.repository.MessageRepository;
import edu.sabanciuniv.it526.repository.UserRepositoy;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;



@RestController
public class WebSocketController {
	
	
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	private UserRepositoy userRepository;
	
	@Autowired
	private MessageRepository messageRespository;
	
	@Autowired
	private ConversationRepositoy conversationRepository;
	
	

	@PostMapping("/send")
	public ResponseEntity<Void> sendMessage(@RequestBody String message) {
		
		JSONObject jsonMessage = new JSONObject(message);
		
		Message msg = new Message();
		
		msg.setSender(userRepository.findUsersById(jsonMessage.getLong("sender")).get(0));
		msg.setText(jsonMessage.getString("text"));
		msg.setCreatedAt(LocalDateTime.now());
		msg.setConversation(conversationRepository.getConversationById(jsonMessage.getInt("convId")));
		
		messageRespository.save(msg);
		
		template.convertAndSend("/topic/message/"+jsonMessage.getInt("convId"), message);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	

	@MessageMapping("/sendMessage")
	@SendTo("/topic/message")
	public String broadcastMessage(@Payload String message) {
		System.out.println("broadcast calisti");
		return message;
	} 
	
	
	/*
	@MessageMapping("/sendMessage")
	public void receiveMessage(@Payload MessageTest message) {
		
		System.out.println(message.toString());
		
	}
	*/
	

}
