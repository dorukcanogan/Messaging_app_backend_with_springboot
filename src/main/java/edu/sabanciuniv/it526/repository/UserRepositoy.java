package edu.sabanciuniv.it526.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

import edu.sabanciuniv.it526.entity.User;

public interface UserRepositoy extends CrudRepository<User,Integer>{
	
	@Query("select u from User u where u.userId= ?1")
    List<User> findUsersById(long userId);
	
	@Query("select u from User u where u.userId = ?1 and u.password = ?2")
	List<User> findUserByIdAndPassword(long userId, String password);
	
	@Query("select u from User u where u.userId = ?1")
	User findUserByEmail(long userId);

}

