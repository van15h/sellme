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
    @RequestMapping(value = "/api/createAdv", method = RequestMethod.POST, produces = "application/json")
    public void create(@RequestParam("token") String token,
                       @RequestParam("email") String email,
                       @RequestBody model.Advertisement advertisement
    ) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/create")
                .queryParam("userId", email)
                .queryParam("token", token);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(
                builder.toUriString(),
                advertisement,
                String.class
        );
    }

    @RequestMapping("/api/advertisements")
    public List<Advertisement> fetchAll(@RequestParam(value = "userId", required = false) String userId) {
        List<Advertisement> advertisements = new ArrayList<>();

        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8080/api/advertisements";
        if (null != userId) {
            url = String.format("http://localhost:8080/api/user%s/advertisements", userId);
        }

        ResponseEntity<List<Advertisement>> responseEntity = rt.exchange(
                url,
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
