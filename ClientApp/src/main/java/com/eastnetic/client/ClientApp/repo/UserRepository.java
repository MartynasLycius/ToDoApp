package com.eastnetic.client.ClientApp.repo;

import com.eastnetic.client.ClientApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
	
	//User findOne(String userName,String password);
	User findByUserNameAndPassword(String userName, String password);

}
