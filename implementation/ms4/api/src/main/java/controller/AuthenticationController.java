package controller;

import com.dse.ms2.model.Advertisement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AuthenticationController {
    @RequestMapping("/api/login")
    public String authenticate(@PathVariable String email, @PathVariable String password) {
        // TODO establish connection to ms1
        return null;
    }

    @RequestMapping("/api/token")
    public String authenticate(@PathVariable String token) {
        // TODO establish connection to ms1
        return null;
    }
}
