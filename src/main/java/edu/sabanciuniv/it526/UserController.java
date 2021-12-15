package edu.sabanciuniv.it526;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import edu.sabanciuniv.it526.entity.Message;
import edu.sabanciuniv.it526.entity.Conversation;
import edu.sabanciuniv.it526.entity.User;
import edu.sabanciuniv.it526.repository.ConversationRepositoy;
import edu.sabanciuniv.it526.repository.MessageRepository;
import edu.sabanciuniv.it526.repository.UserRepositoy;
import java.time.*;

import org.json.*;



@RestController
public class UserController {


	@Autowired
	private UserRepositoy userRepository;
	
	@Autowired
	private MessageRepository messageRespository;
	
	@Autowired
	private ConversationRepositoy conversationRepository;
	

	@GetMapping("/messages")
	public Iterable<Message> getAllMessages()
	{

		return messageRespository.findAll();
	}
	
	@PostMapping("/register")
	public User register(@RequestBody String strUser) {

		JSONObject jsonUser = new JSONObject(strUser);
		
		User usr = new User(jsonUser.getLong("userId"),jsonUser.getString("firstName"),
				jsonUser.getString("lastName"), jsonUser.getString("password"),
				jsonUser.getString("email"),LocalDateTime.now());
		
		User registeredUser = userRepository.save(usr);
		
		System.out.println("kaydedildi");
		
		return registeredUser;
		
	}
	
	@PostMapping("/login")
	public List<User> login(@RequestBody String strUser) {
		
		JSONObject jsonUser = new JSONObject(strUser);
		
		return userRepository.findUserByIdAndPassword(jsonUser.getLong("userId"), jsonUser.getString("password"));
		
	}
	
	@PostMapping("/remember")
	public User remember(@RequestBody String strUser) {
		
		JSONObject jsonUser = new JSONObject(strUser);
	
		
		return userRepository.findUserByEmail(jsonUser.getLong("userId"));
		
	}
	
	
	@GetMapping("/conversations")
	public List<Conversation> getAllConversations(@RequestParam(name = "id") long userId)
	{	

		return conversationRepository.getConversationsByUserId(userId);
	}
	
	@GetMapping("/load")
	public void testData()
	{	
		
		User user1 = new User(5379686676L,"Doruk Can", "Ogan", "12345678","dcanogan@gmail.com",LocalDateTime.now());
		
		user1.setHasStory(true);
		
		User user2 = new User(5058053766L,"Cagan", "Ogan", "12345678","cgnogn@gmail.com",LocalDateTime.now());
		
		user2.setHasStory(true);
		
		User user3 = new User(5058053755L,"Ali Riza", "Ogan", "12345678","aliriza.ogan@gmail.com",LocalDateTime.now());
		
		user3.setHasStory(true);
		
		User user4 = new User(5058053759L,"Sebahat", "Ogan", "12345678","sebahatogan@gmail.com",LocalDateTime.now());
		
		user4.setHasStory(true);
		
		User user5 = new User(5444519533L,"Simge", "Ertuk", "12345678","ozgeogan@gmail.com",LocalDateTime.now());
		
		user5.setHasStory(true);
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		userRepository.save(user5);
		
		
		List<User> users = new ArrayList<User>();
		
		users.add(user1);
		users.add(user2);
		
		Conversation con1 = new Conversation(users, LocalDateTime.now());
		
		conversationRepository.save(con1);
		
		List<User> users2 = new ArrayList<User>();
		
		users2.add(user1);
		users2.add(user3);
		
		Conversation con2 = new Conversation(users2, LocalDateTime.now());
		
		conversationRepository.save(con2);
		
		List<User> users3 = new ArrayList<User>();
		
		users3.add(user1);
		users3.add(user5);
		
		Conversation con3 = new Conversation(users3, LocalDateTime.now());
		
		conversationRepository.save(con3);
		
		List<User> users4 = new ArrayList<User>();
		
		users4.add(user3);
		users4.add(user4);
		
		Conversation con4 = new Conversation(users4, LocalDateTime.now());
		
		conversationRepository.save(con4);
		
		Message message1 = new Message(con1, user1, "Abicim naber nasilsin?", 't', LocalDateTime.now());
		
		messageRespository.save(message1);
		
		Message message2 = new Message(con1, user2, "iyiyim sen nasilsin?",'t', LocalDateTime.now());
		
		messageRespository.save(message2);
		
		Message message3 = new Message(con2, user1, "babacim nasilsin?",'t', LocalDateTime.now());
		
		messageRespository.save(message3);
		
		Message message4 = new Message(con2, user3, "oglum iyiyim sen?",'t', LocalDateTime.now());
		
		messageRespository.save(message4);
		
		Message message5 = new Message(con4, user3, "Sebahat hanım nasilsin?",'t', LocalDateTime.now());
		
		messageRespository.save(message5);
		
		Message message6 = new Message(con4, user3, "iyiyim bey sen nasilsin?",'t', LocalDateTime.now());
		
		messageRespository.save(message6);
		
		Message message7 = new Message(con3, user1, "Simgecim, partiye geliyor musun?",'t', LocalDateTime.now());
		
		messageRespository.save(message7);
		
		Message message8 = new Message(con3, user5, "Evet bunu asla kacirmam!",'t', LocalDateTime.now());
		
		messageRespository.save(message8);
		
		
		
	}
	
}
