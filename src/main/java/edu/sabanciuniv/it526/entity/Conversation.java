package edu.sabanciuniv.it526.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Conversation")
public class Conversation implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int conversationId;
	
	@JsonManagedReference
	@ManyToMany
	private List<User> users;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "conversation")
	private List<Message> messages;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	public Conversation(List<User> users, LocalDateTime createdAt) {
		super();
		this.users = users;
		this.createdAt = createdAt;
	}

	
	
	
	
	

	


	
}
