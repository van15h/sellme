package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.dse.ms2.model.Advertisement;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AdvertisementController {
    @RequestMapping(value = "/api/createAdv", method = RequestMethod.POST)
    public void create() {
        HttpHeaders headers = new HttpHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/create")
                .queryParam("userId", "12")
                .queryParam("token", "meier");

        headers.setContentType(MediaType.APPLICATION_JSON);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        model.Advertisement myAdvertisement = new model.Advertisement(12, "Meier", 123, "Hubert", "abc@gmail.com");
        gson.toJson(myAdvertisement);

        HttpEntity<String> request = new HttpEntity<>(gson.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.postForEntity(
                builder.toUriString(),
                myAdvertisement,
                String.class
        );
    }

    @RequestMapping("/api/advertisements")
    public List<Advertisement> fetchAll() {
        List<Advertisement> advertisements = new ArrayList<>();
        RestTemplate rt = new RestTemplate();
        ResponseEntity<List<Advertisement>> responseEntity = rt.exchange(
                "http://localhost:8080/api/advertisements",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Advertisement>>() {}
        );

        advertisements = responseEntity.getBody();


        return advertisements;
    }

    @RequestMapping("/api/advertisements/{id}")
    public Advertisement get(@PathVariable("id") int id ) {
        RestTemplate rt = new RestTemplate();

        return rt.getForObject(
                String.format("http://localhost:8080/api/advertisements/%d", id),
                Advertisement.class
        );
    }
}
