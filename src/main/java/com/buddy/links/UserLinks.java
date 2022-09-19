package com.buddy.links;

import org.springframework.stereotype.Component;

@Component
public class UserLinks {
	
	public static final String LIST_USERS = "/users";
    public static final String ADD_USER = "/add-user";
    public static final String GENERATE_BUDDY = "/user/{id}/buddy";
    public static final String GET_USER_BY_ID = "/user/{id}";
    public static final String UPDATE_USER = "/update-user/{id}";
    public static final String REMOVE_USER = "/remove-user/{id}";
}
