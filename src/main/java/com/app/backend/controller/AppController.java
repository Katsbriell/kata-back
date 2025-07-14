package com.app.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.backend.dto.EmailDTO;
import com.app.backend.model.OnbEntity;
import com.app.backend.model.UsersEntity;
import com.app.backend.service.AppService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/create-user")
    public ResponseEntity<UsersEntity> createUser(@RequestBody UsersEntity user) {
        return appService.createUser(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsersEntity>> getUsers() {
        return appService.getAllUsers();
    }

    @PutMapping("/update-users")
    public ResponseEntity<List<UsersEntity>> updateUsers(@RequestBody List<UsersEntity> users) {
        return appService.updateUsers(users);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return appService.deleteUser(id);
    }

    @GetMapping("/onboardings")
    public ResponseEntity<List<OnbEntity>> getOnb() {
        return appService.getAllOnbs();
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) {
        return appService.sendEmail(emailDTO);
    }
}
