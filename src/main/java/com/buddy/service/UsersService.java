package com.buddy.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.buddy.exception.ResourceNotFoundException;
import com.buddy.model.Users;
import com.buddy.repository.UsersRepository;


@Component
public class UsersService {

	private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public  Users getUser(Integer userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("The user with id " + userId + " doesn't exist."));
    } 
    public List<Users> getUsers() {
        return usersRepository.findAll();
    }
    
    public Users saveUser(Users users) {
    	return usersRepository.save(users);
    }
    
    public void deleteUser(Integer userId) {
    	 usersRepository.deleteById(userId);
    }
}
