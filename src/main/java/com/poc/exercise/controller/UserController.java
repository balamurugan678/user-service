package com.poc.exercise.controller;

import com.poc.exercise.models.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class UserController {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsersList(RestTemplate restTemplate, HttpServletResponse httpResponse) {
        ResponseEntity<List<User>> usersResponse =
                restTemplate.exchange("http://jsonplaceholder.typicode.com/users",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> users = usersResponse.getBody();
        addResponseheaders(httpResponse);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    private void addResponseheaders(HttpServletResponse httpResponse) {
        httpResponse.setHeader("Cache-Control", "public");
        httpResponse.setHeader("Connection", "keep-alive");
        httpResponse.setHeader("Content-Type", "application/json");
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(RestTemplate restTemplate, @PathVariable("userId") String userId) {
        User user = restTemplate.getForObject("http://jsonplaceholder.typicode.com/users/" + userId, User.class);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(RestTemplate restTemplate, @RequestBody User user) throws Exception {
        User createdUser = restTemplate.postForObject("http://jsonplaceholder.typicode.com/users", user, User.class);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(RestTemplate restTemplate, @PathVariable("userId") String userId) {
        restTemplate.delete("http://jsonplaceholder.typicode.com/users/" + userId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
