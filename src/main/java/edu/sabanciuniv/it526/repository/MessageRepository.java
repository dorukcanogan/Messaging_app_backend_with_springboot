package edu.sabanciuniv.it526.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.sabanciuniv.it526.entity.Message;


public interface MessageRepository extends CrudRepository<Message,Integer> {

	@Query("select m from Message m where m.messageId= ?1 ")
    Message findmessagesByconsId(Integer messageId);
	
	@Query("select m from Message m")
    List<Message> findmessage();
	
	@Query("select m from Message m")
    List<Message> findAllMessage();
	
	
	
}
