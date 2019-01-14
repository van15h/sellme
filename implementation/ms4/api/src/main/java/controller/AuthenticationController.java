package controller;

import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin
public class AuthenticationController {
    @RequestMapping("/api/login")
    public String authenticate(@RequestParam("email") String email, @RequestParam("password") String password) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme("http")
                .host(environment.Environment.MS1)
                .port(environment.Environment.MS1_PORT)
                .path("/login")
                .queryParam("username", email)
                .queryParam("password", password);

        String response = restTemplate.getForObject(
                builder.toUriString(),
                String.class
        );

        return response;
    }

    @RequestMapping("/api/token/{token}")
    public boolean authenticate(@PathVariable String token) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme("http")
                .host(environment.Environment.MS1)
                .port(environment.Environment.MS1_PORT)
                .path(String.format("/token/%s", token));

        return restTemplate.getForObject(builder.toUriString(), Boolean.class);
    }

    @RequestMapping(
            value = "api/register",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public String register(@RequestBody User user) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme("http")
                .host(environment.Environment.MS1)
                .port(environment.Environment.MS1_PORT)
                .path("register")
                .queryParam("email", user.getEmail())
                .queryParam("password", user.getPassword())
                .queryParam("name", user.getName());

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}
