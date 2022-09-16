package com.purple.dao;

import javax.sql.DataSource;

import com.purple.model.User;
import com.purple.model.UserSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserDaoJdbc implements UserDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public UserDaoJdbc(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public User saveUser(User user) {
		String sql = (user.getId()>0) ?
			"update app_user set " +
					" first_name=:first_name, " +
					" last_name=:last_name, " +
					//" role=:role, " +
					" profile_image_id=:profile_image_id " +
					" where id=:id;"
		:
			"insert into app_user" +
					" (user_name, password, first_name, last_name, role, salt, active,profile_image_id) " +
					" values " +
					" (:user_name, :password, :first_name, :last_name, :role, :salt, true, :profile_image_id)" +
					" returning id;";

		Map<String,Object> params = new HashMap<>();
		params.put("id",user.getId());
		params.put("user_name",user.getUserName());
		params.put("password",user.getPassword());
		params.put("first_name",user.getFirstName());
		params.put("last_name",user.getLastName());
		params.put("role",user.getRoleString());
		params.put("salt",user.getSalt());
		params.put("profile_image_id",user.getProfileImageId());
		if (user.getId() == 0) {
			Long id = jdbcTemplate.queryForObject(sql, params, Long.class);
			return getUserById(id);
		} else {
			jdbcTemplate.update(sql, params);
		}

		return getUserById(user.getId());
	}

	@Override
	public void updatePassword(String userName, String password, String salt) {
		String sql = """
				update app_user
				set
				    password = :password,
				    salt = :salt
				where
				    user_name = :username;""";
		Map<String,Object> params = new HashMap<>();
		params.put("user_name",userName);
		params.put("password",password);
		params.put("salt",salt);
		jdbcTemplate.update(sql,params);
	}
	@Override
	public void deleteUser(Long id) {
		String sql = "update app_user " +
				" set active=false " +
				" where id = :id";
		Map<String,Object> params = new HashMap<>();
		params.put("id",id);
		jdbcTemplate.update(sql,params);
	}

	@Override
	public User getUserByUserName(String userName, boolean returnPassword) {
		String sqlSearchForUsername ="select * "+
		" from app_user "+
		" where upper(user_name) = :username  and active=true";
		Map<String,Object> params = new HashMap<>();
		params.put("username",userName.toUpperCase());
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSearchForUsername, params);
		if(rowSet.next()) {
			return mapUserFromRowSet(rowSet,returnPassword);
		}

		return null;
	}
	@Override
	public User getUserById(Long id) {
		String sqlSearchForUsername ="select * "+
				" from app_user "+
				" where id = :id and active=true;";
		Map<String,Object> params = new HashMap<>();
		params.put("id",id);
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSearchForUsername, params);
		if(rowSet.next()) {
			return mapUserFromRowSet(rowSet,true);
		}
		return null;
	}
	@Override
	public List<User> getUsers(User currentUser, UserSearchCriteria searchCriteria) {
		String sql ="select * "+
				" from app_user "+
				" where  active=true " +
				" and (" +
				" first_name ilike :name " +
				" or " +
				" last_name ilike :name " +
				" ) ;";
		Map<String,Object> params = new HashMap<>();
		params.put("name",searchCriteria.getName());
		//params.put("groupname",searchCriteria.getGroupName());
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, params);
		return mapUsersFromRowSets(rowSet,false);
	}

	private List<User> mapUsersFromRowSets(SqlRowSet results,boolean returnPassword) {
		List<User> users = new ArrayList<>();
		while(results.next()) {
			users.add(mapUserFromRowSet(results,returnPassword));
		}
		return users;
	}
	private User mapUserFromRowSet(SqlRowSet results, boolean returnPassword) {
		User thisUser;
		thisUser = new User();
		thisUser.setId(results.getLong("id"));
		thisUser.setUserName(results.getString("user_name"));
		thisUser.setPassword(results.getString("password"));
		thisUser.setFirstName(results.getString("first_name"));
		thisUser.setLastName(results.getString("last_name"));
		thisUser.setProfileImageId(results.getLong("profile_image_id"));
		thisUser.setRole(User.UserRole.fromString(results.getString("role")));

		if (returnPassword){
			thisUser.setPassword(results.getString("password"));
			thisUser.setSalt(results.getString("salt"));
		}
		return thisUser;
	}

}
