package controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.dse.ms2.model.Advertisement;

@RestController
@CrossOrigin
public class AdvertisementController {
    @RequestMapping(value = "/api/createAdv", method = RequestMethod.POST)
    public void create() {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForObject("http://localhost:8080/api/advertisements")
    }
}
