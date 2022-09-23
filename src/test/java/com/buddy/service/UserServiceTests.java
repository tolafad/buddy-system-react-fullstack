package com.buddy.service;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buddy.exception.ResourceNotFoundException;
import com.buddy.model.Users;
import com.buddy.repository.UsersRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    private Users users;

    @BeforeEach
    public void setup(){
        //usersRepository = Mockito.mock(UsersRepository.class);
        //usersService = new UsersServiceImpl(usersRepository);
        users = Users.builder()
                .withId(1)
                .withName("tola fad")
                .withEmail("tolafad.com")
                .build();
    }

    // JUnit test for saveUsers method
    @DisplayName("JUnit test for saveUsers method")
    @Test
    public void givenUsersObject_whenSaveUsers_thenReturnUsersObject(){
        // given - precondition or setup
        given(usersRepository.save(users)).willReturn(users);

        System.out.println(usersRepository);
        System.out.println(usersService);

        // when -  action or the behaviour that we are going test
        Users savedUsers = usersService.saveUser(users);

        System.out.println(savedUsers);
        // then - verify the output
        assertThat(savedUsers).isNotNull();
    }

    // JUnit test for saveUsers method
    @DisplayName("JUnit test for saveUsers method which throws exception")
    @Test
    public void givenNonExistingID_thenThrowsException(){
        // given - precondition or setup
    	
    	Users users1 = Users.builder()
                .withId(200)
                .withName("Jethro Gibbs")
                .withEmail("gibbs@ncis.com")
                .build();
    	
        System.out.println(usersRepository);
        System.out.println(usersService);

        // when -  action or the behaviour that we are going test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            usersService.getUser(users1.getId());
        });

        // then
        verify(usersRepository, never()).save(any(Users.class));
    }

    // JUnit test for getAllUsers method
    @DisplayName("JUnit test for getAllUsers method")
    @Test
    public void givenUsersList_whenGetAllUsers_thenReturnUsersList(){
        // given - precondition or setup

        Users users1 = Users.builder()
                .withId(2)
                .withName("Jethro Gibbs")
                .withEmail("gibbs@ncis.com")
                .build();

        given(usersRepository.findAll()).willReturn(List.of(users,users1));

        // when -  action or the behaviour that we are going test
        List<Users> usersList = usersService.getUsers();

        // then - verify the output
        assertThat(usersList).isNotNull();
        assertThat(usersList.size()).isEqualTo(2);
    }

    // JUnit test for getAllUsers method
    @DisplayName("JUnit test for getAllUsers method (negative scenario)")
    @Test
    public void givenEmptyUsersList_whenGetAllUsers_thenReturnEmptyUsersList(){
        // given - precondition or setup

    	 Users users1 = Users.builder()
                 .withId(2)
                 .withName("Jethro Gibbs")
                 .withEmail("gibbs@ncis.com")
                 .build();

        given(usersRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Users> usersList = usersService.getUsers();

        // then - verify the output
        assertThat(usersList).isEmpty();
        assertThat(usersList.size()).isEqualTo(0);
    }

    // JUnit test for getUsersById method
    @DisplayName("JUnit test for getUsersById method")
    @Test
    public void givenUsersId_whenGetUsersById_thenReturnUsersObject(){
        // given
        given(usersRepository.findById(1)).willReturn(Optional.of(users));

        // when
        Users savedUsers = usersService.getUser(users.getId());

        // then
        assertThat(savedUsers).isNotNull();

    }

    // JUnit test for updateUsers method
    @DisplayName("JUnit test for updateUsers method")
    @Test
    public void givenUsersObject_whenUpdateUsers_thenReturnUpdatedUsers(){
        // given - precondition or setup
        given(usersRepository.save(users)).willReturn(users);
        users.setEmail("tony@ncis.com");
        users.setName("Tony Jr");
        // when -  action or the behaviour that we are going test
        Users updatedUsers = usersService.saveUser(users);

        // then - verify the output
        assertThat(updatedUsers.getEmail()).isEqualTo("tony@ncis.com");
        assertThat(updatedUsers.getName()).isEqualTo("Tony Jr");
    }

    // JUnit test for deleteUsers method
    @DisplayName("JUnit test for deleteUsers method")
    @Test
    public void givenUsersId_whenDeleteUsers_thenNothing(){
        // given - precondition or setup
        int usersId = 1;

        willDoNothing().given(usersRepository).deleteById(usersId);

        // when -  action or the behaviour that we are going test
        usersService.deleteUser(usersId);

        // then - verify the output
        verify(usersRepository, times(1)).deleteById(usersId);
    }
}