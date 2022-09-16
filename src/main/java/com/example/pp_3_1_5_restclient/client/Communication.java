package com.example.pp_3_1_5_restclient.client;

import com.example.pp_3_1_5_restclient.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class Communication {

    private final RestTemplate restTemplate;

    private final HttpHeaders headers;

    private final StringBuilder code;

    private final String URL = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        code = new StringBuilder();
    }


    public StringBuilder getCode() {

        return code;
    }


    // List<User>
    public String getAllUsers() {

        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<String> responseEntity = restTemplate.exchange(
                URL, HttpMethod.GET, entity, String.class);

        String sessionId = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        headers.add("Cookie", sessionId);

        System.out.println(sessionId);

        String string = responseEntity.getBody();

        return string;
    }

    public void addUser(User user) {

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                URL, HttpMethod.POST, entity, String.class);

        code.append(responseEntity.getBody());
    }

    public void updateUser(User user) {

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                URL, HttpMethod.PUT, entity, String.class);

        code.append(responseEntity.getBody());
    }

    public void deleteUser(User user, Long id) {

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                URL + "/" + id, HttpMethod.DELETE, entity, String.class);

        code.append(responseEntity.getBody());
    }

}
