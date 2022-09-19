package com.buddy.controller;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddy.links.UserLinks;
import com.buddy.model.Users;
import com.buddy.service.UsersService;
import com.google.common.collect.Lists;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/buddy-system")
public class UsersController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UsersController.class);

	@Autowired
	UsersService usersService;

	@GetMapping(path = UserLinks.LIST_USERS)
	public ResponseEntity<List<Users>> listUsers() {
		log.info("UsersController:  list users");
		List<Users> resource = usersService.getUsers();

		return ResponseEntity.ok(resource);
	}

	@PostMapping(path = UserLinks.ADD_USER)
	public ResponseEntity<?> saveUser(@RequestBody Users user) {
		log.info("UsersController:  add user");
		Users resource = usersService.saveUser(user);
		return ResponseEntity.ok(resource);
	}

	@PutMapping(path = UserLinks.UPDATE_USER)
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") Integer userId,
			@RequestBody Users user) {
		log.info("UsersController:  update user");
		log.info("UsersController:  get user for {}", userId);
		Users u = usersService.getUser(userId);
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		Users resource = usersService.saveUser(u);
		return ResponseEntity.ok(resource);
	}
	
	@DeleteMapping(path = UserLinks.REMOVE_USER)
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userId) {
		log.info("UsersController:  delete user");
		log.info("UsersController:  get user for {}", userId);
		Users u = usersService.getUser(userId);
		usersService.deleteUser(userId);
		return ResponseEntity.ok("success");
	}
	
	@GetMapping(path = UserLinks.GET_USER_BY_ID)
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") Integer userId) {
		log.info("UsersController:  get user for {}", userId);
		Users user = usersService.getUser(userId);	
		return ResponseEntity.ok(user);		
	}
	
	@GetMapping(path = UserLinks.GENERATE_BUDDY)
	public ResponseEntity<?> genenrateAndRetrieveBuddy(@PathVariable(value = "id") Integer userId) {
		log.info("UsersController:  genenrate and Retrieve Buddy for {}", userId);
		Users user = usersService.getUser(userId);
		if (user.getBuddy() != null) {
			return ResponseEntity.ok(user);
		}

		List<Users> givenUsers = new ArrayList<Users>(usersService.getUsers());
		givenUsers.removeAll(getBuddies(new ArrayList<Users>(givenUsers)));
		givenUsers.remove(user);

		log.info("givenUsers : {}", givenUsers);

		final Map<Boolean, List<Users>> lists = givenUsers.stream()
				.collect(Collectors.partitioningBy(n -> n.getBuddy() == null));
		List<Users> withoutBuddyUsers = lists.get(true);
		List<Users> withBuddyUsers = lists.get(false);
		log.info("Without buddy {}", withoutBuddyUsers);
		log.info("With buddy {}", withBuddyUsers);

		Users randomUser = getRandomBuddy(givenUsers);
		if (givenUsers.size() == 2 && withoutBuddyUsers.size() > 0) {
			randomUser = getRandomBuddy(withoutBuddyUsers);
		} else {
			log.info("With withBuddyUsers.size() > 0 =  {}", withBuddyUsers.size() > 0);

			randomUser = withBuddyUsers.size() >= withoutBuddyUsers.size() ? getRandomBuddy(withBuddyUsers) : getRandomBuddy(withoutBuddyUsers);
		}

		user.setBuddy(randomUser);

		Users resource = usersService.saveUser(user);
		return ResponseEntity.ok(resource);
	}

	private Users getRandomBuddy(List<Users> givenUsers) {
		Collections.shuffle(givenUsers);
		Random rand = new Random();
		Users randomUser = givenUsers.get(rand.nextInt(givenUsers.size()));
		return randomUser;
	}

	private List<Users> getBuddies(List<Users> users) {
		List<Users> buddies = Lists.transform(users, new com.google.common.base.Function<Users, Users>() {

			public Users apply(Users n) {
				return n.getBuddy();
			}
		});
		buddies.removeIf(buddy -> buddy == null);
		return buddies;
	}
}
