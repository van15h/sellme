package controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.dse.ms2.model.Advertisement;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class TestController {
    @RequestMapping("/")
    public String index() {
        List<Advertisement> advertisements = new ArrayList<>();
        RestTemplate rt = new RestTemplate();
        ResponseEntity<List<Advertisement>> responseEntity = rt.exchange(
                "http://localhost:8080/api/advertisements",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Advertisement>>() {}
        );

        advertisements = responseEntity.getBody();


        return "Hello world";
    }
}
