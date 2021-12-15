package edu.sabanciuniv.it526.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User implements Serializable {
	
	@Id
	private long userId;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private boolean hasStory;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private List<Contacts> contacts;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@JsonBackReference
	@ManyToMany(mappedBy = "users")
	private List<Conversation> conversations;

	@JsonBackReference
	@OneToMany(mappedBy = "sender")
	private List<Message> messages;

	public User(long userId, String firstName, String lastName, 
			String password,String email, LocalDateTime createdAt) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.createdAt = createdAt;
	}

	
}
