package com.purple.dao;
import com.purple.model.User;
import com.purple.model.UserSearchCriteria;

import java.util.List;

public interface UserDao {

	User saveUser(User user);
	void updatePassword(String userName, String password, String salt);

	User getUserByUserName(String userName, boolean returnPassword);
	User getUserById(Long id);
	void deleteUser(Long id);
	List<User> getUsers(User currentUser, UserSearchCriteria searchCriteria);
}
