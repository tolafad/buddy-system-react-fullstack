package com.buddy.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.buddy.model.Users;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UsersRepositoryTests {

    @Autowired
    private UsersRepository usersRepository;

    private Users users;

    @BeforeEach
    public void setup(){
    	 users = Users.builder()
                 .withId(1)
                 .withName("tola fad")
                 .withEmail("tolafad.com")
                 .build();
    }

    // JUnit test for save users operation
    //@DisplayName("JUnit test for save users operation")
    @Test
    public void givenUsersObject_whenSave_thenReturnSavedUsers(){

        //given - precondition or setup
    	 users = Users.builder()
                 .withId(1)
                 .withName("tola fad")
                 .withEmail("tolafad.com")
                 .build();
        // when - action or the behaviour that we are going test
        Users savedUsers = usersRepository.save(users);

        // then - verify the output
        assertThat(savedUsers).isNotNull();
        assertThat(savedUsers.getId()).isGreaterThan(0);
    }


     
    // JUnit test for get users by id operation
    @DisplayName("JUnit test for get users by id operation")
    @Test
    public void givenUsersObject_whenFindById_thenReturnUsersObject(){

        usersRepository.save(users);

        // when -  action or the behaviour that we are going test
        Users usersDB = usersRepository.findById(users.getId()).get();

        // then - verify the output
        assertThat(usersDB).isNotNull();
    }

    // JUnit test for update users operation
    @DisplayName("JUnit test for update users operation")
    @Test
    public void givenUsersObject_whenUpdateUsers_thenReturnUpdatedUsers(){

        usersRepository.save(users);

        // when -  action or the behaviour that we are going test
        Users savedUsers = usersRepository.findById(users.getId()).get();
        savedUsers.setEmail("tolafad.com");
        savedUsers.setName("tola fad");
        Users updatedUsers =  usersRepository.save(savedUsers);

        // then - verify the output
        assertThat(updatedUsers.getEmail()).isEqualTo("tolafad.com");
        assertThat(updatedUsers.getName()).isEqualTo("tola fad");
    }

    // JUnit test for delete users operation
    @DisplayName("JUnit test for delete users operation")
    @Test
    public void givenUsersObject_whenDelete_thenRemoveUsers(){

        usersRepository.save(users);

        // when -  action or the behaviour that we are going test
        usersRepository.deleteById(users.getId());
        Optional<Users> usersOptional = usersRepository.findById(users.getId());

        // then - verify the output
        assertThat(usersOptional).isEmpty();
    }
}
