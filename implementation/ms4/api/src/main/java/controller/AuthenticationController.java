package controller;

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
                .host(environment.Environment.ms1)
                .port(8011)
                .path("/login")
                .queryParam("username", email)
                .queryParam("password", password);

        String response = restTemplate.getForObject(
                builder.toUriString(),
                String.class
        );

        return response;
    }

    @RequestMapping("/api/token")
    public String authenticate(@PathVariable String token) {
        // TODO establish connection to ms1
        return null;
    }
}
