package com.sofka.clientspersonsservice.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sofka.clientspersonsservice.application.dto.AuthUserDto;
import com.sofka.clientspersonsservice.application.dto.UserDto;
import com.sofka.clientspersonsservice.application.service.UserService;
import com.sofka.clientspersonsservice.presentation.request.RegisterRequest;
import com.sofka.clientspersonsservice.presentation.request.UserUpdateRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService clientespersonasservice;
    private final ModelMapper modelMapper;

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(modelMapper.map(clientespersonasservice.saveUser(request), UserDto.class));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(clientespersonasservice.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class)).toList());
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(modelMapper.map(clientespersonasservice.getUserById(id), UserDto.class));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(modelMapper.map(clientespersonasservice.getUserByEmail(email), UserDto.class));
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<AuthUserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(modelMapper.map(clientespersonasservice.getUserByUsername(username), AuthUserDto.class));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or @clientespersonasservice.getUserById(#request.id).username == principal")
    public ResponseEntity<UserDto> updateUserById(@Valid @RequestPart UserUpdateRequest request,
                                                  @RequestPart(required = false) MultipartFile file) {
        return ResponseEntity.ok(modelMapper.map(clientespersonasservice.updateUserById(request, file), UserDto.class));
    }

    @DeleteMapping("/deleteUserById/{id}")
    @PreAuthorize("hasRole('ADMIN') or @clientespersonasservice.getUserById(#id).username == principal")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        clientespersonasservice.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
