package edu.sabanciuniv.it526.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Contacts")
public class Contacts implements Serializable {
	
	@Id
	private int contactId;
	
	@JsonManagedReference
	@ManyToOne
	private User user;

	public Contacts(User user) {
		super();
		this.user = user;
	}
	
	
	
	
}
