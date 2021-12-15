package edu.sabanciuniv.it526.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

import edu.sabanciuniv.it526.entity.Conversation;

public interface ConversationRepositoy extends CrudRepository<Conversation,Integer>{
	

	@Query("SELECT c FROM Conversation c JOIN c.users u where u.userId= ?1")
	List<Conversation> getConversationsByUserId(long userId);

	@Query("SELECT c FROM Conversation c where c.conversationId= ?1")
	Conversation getConversationById(int convId);
	

}

