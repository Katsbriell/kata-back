package com.app.backend.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.backend.dto.EmailDTO;
import com.app.backend.model.OnbEntity;
import com.app.backend.model.UsersEntity;

public interface AppService {

    ResponseEntity<UsersEntity> createUser(UsersEntity user);

    ResponseEntity<List<UsersEntity>> getAllUsers();

    ResponseEntity<List<UsersEntity>> updateUsers(List<UsersEntity> users);

    ResponseEntity<Void> deleteUser(Long id);

    ResponseEntity<List<OnbEntity>> getAllOnbs();

    ResponseEntity<String> sendEmail(EmailDTO emailDTO);
}
