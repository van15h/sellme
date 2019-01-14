package controller;

import com.dse.ms2.model.Advertisement;
import com.dse.ms2.model.Environment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AdvertisementController {
    @RequestMapping(value = "/api/createAdv", method = RequestMethod.POST, produces = "application/json")
    public void create(@RequestParam("token") String token,
                       @RequestParam("email") String email,
                       @RequestBody model.Advertisement advertisement
    ) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(String.format("%s/api/create", environment.Environment.MS2_LOCAL))
                .queryParam("userId", email)
                .queryParam("token", token);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(
                builder.toUriString(),
                advertisement,
                String.class
        );
    }

    @RequestMapping(value = "/api/updateAdv/{id}", method = RequestMethod.PUT, produces = "application/json")
    public void update(@PathVariable("id") int id,
                       @RequestParam("token") String token,
                       @RequestParam("email") String email,
                       @RequestBody model.Advertisement advertisement
    ) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format("%s/api/update", environment.Environment.MS2_LOCAL))
                .path(String.format("/%d", id))
                .queryParam("userId", email)
                .queryParam("token", token);

        HttpEntity<model.Advertisement> requestEntity = new HttpEntity<>(advertisement);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.PUT,
                requestEntity,
                String.class
        );
    }

    /**
     * /api/deleteAdv/{id} URI to delete custom advertisement
     * @param id unique id if advertisement
     * @param token user token to check session time
     */
    @RequestMapping(value = "/api/deleteAdv/{id}", method = RequestMethod.DELETE)
    public void delete (
            @PathVariable("id") int id,
            @RequestParam(value = "token") String token
    )
    {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format("%s/api/delete/%d", environment.Environment.MS2_LOCAL, id))
                .queryParam("token", token);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(builder.toUriString());
    }


    @RequestMapping("/api/advertisements")
    public List<Advertisement> fetchAll(@RequestParam(value = "userId", required = false) String userId) {
        List<Advertisement> advertisements = new ArrayList<>();

        RestTemplate rt = new RestTemplate();
        String url = environment.Environment.MS2_LOCAL + "/api/advertisements";
        if (null != userId) {
            url = String.format("%s/api/user%s/advertisements", environment.Environment.MS2_LOCAL, userId);
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
            String.format("%s/api/advertisements/%d", environment.Environment.MS2_LOCAL, id),
            Advertisement.class
        );
    }
}
