package com.example.userapidemo.controller;

import com.example.userapidemo.dto.SaveUserDTO;
import com.example.userapidemo.entity.User;
import com.example.userapidemo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity<List<User>> listUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody SaveUserDTO user) {
        User savedUser = userRepository.save(User.builder()
                .name(user.getName())
                .sex(user.getSex())
                .age(user.getAge())
                .build());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return new ResponseEntity<>(optionalUser.orElse(null),
                optionalUser.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
