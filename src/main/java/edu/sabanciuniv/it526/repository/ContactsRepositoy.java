package edu.sabanciuniv.it526.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sabanciuniv.it526.entity.Contacts;

public interface ContactsRepositoy extends CrudRepository<Contacts,Integer>{
	
	
}

