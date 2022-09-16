package com.purple.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Locale;

public class User {
    public enum UserRole {
        Standard,
        Admin,
        Vip;

        public static UserRole fromString(String value){
            if (value != null) {
                for (UserRole each : UserRole.values()) {
                    if (each.name().compareToIgnoreCase(value) == 0) {
                        return each;
                    }
                }
            }
            return Standard;
        }
    }
	private long id;
    private UserRole role;

	private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String confirmPassword;

    private String firstName;

    private String lastName;

    private String token;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String salt;

    private Long profileImageId;

    public void setId(long id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User(){
    	this.role = UserRole.Standard;
	}

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the role
     */
    public UserRole getRole() {

    	return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(UserRole role) {

    	this.role = role;
        Date.valueOf(LocalDateTime.now().toLocalDate());
    }
    public String getRoleString() {
        return this.role.toString();
    }

    public String getUserName() {

    	return userName;
    }

    public void setUserName(String userName) {

    	this.userName = userName;
    }

    public String getPassword() {

    	return password;
    }

    public void setPassword(String password) {

    	this.password = password;
    }

    public String getConfirmPassword() {

    	return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {

    	this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isMemberOfRole(String userRoleRequired) {
        return ((this.role != null) && (this.role.toString().equalsIgnoreCase(userRoleRequired)));
    }
    public boolean isMemberOfRole(UserRole userRoleRequired) {
        return ((this.role != null) && (this.role.equals(userRoleRequired)));
    }

    public Long getProfileImageId() {
        return profileImageId;
    }

    public void setProfileImageId(Long profileImageId) {
        this.profileImageId = profileImageId;
    }
}
