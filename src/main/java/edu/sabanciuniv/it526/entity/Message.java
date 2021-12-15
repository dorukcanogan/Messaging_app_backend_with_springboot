package edu.sabanciuniv.it526.entity;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Message")
public class Message implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messageId;
	
	@JsonBackReference
	@ManyToOne
	private Conversation conversation;

	@JsonManagedReference
	@ManyToOne
	private User sender;
	
	private String text;
	
	private char messageType; 
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
	

	public Message(Conversation conversation, User sender, String text,
			char messageType, LocalDateTime createdAt) {
		super();
		this.conversation = conversation;
		this.sender = sender;
		this.text = text;
		this.messageType = messageType;
		this.createdAt = createdAt;
	}
	
	
	
	
	

	
}
