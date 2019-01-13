package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.dse.ms2.model.Advertisement;

import javax.xml.ws.Response;

@RestController
@CrossOrigin
public class AdvertisementController {
    @RequestMapping(value = "/api/createAdv", method = RequestMethod.POST)
    public void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("userId", "userId");
//        headers.add("token", "mrmrmrmr");

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        model.Advertisement myAdvertisement = new model.Advertisement(12, "Meier", 123, "Hubert", "abc@gmail.com");
        gson.toJson(myAdvertisement);

        HttpEntity<String> request = new HttpEntity<>(gson.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.postForObject(
                "http://localhost:8080/api/create",
                request,
                ResponseEntity.class
        );
    }
}
