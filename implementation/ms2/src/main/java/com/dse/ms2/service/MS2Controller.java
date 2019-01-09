package com.dse.ms2.service;

import com.dse.ms2.model.Advertisement;
import com.dse.ms2.model.Environment;
import com.dse.ms2.model.IRepository;
import com.dse.ms2.model.InMemoryRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Controller class to handle all requests from the client.
 * Following paths are available:
 *            api/advertisements
 *            api/advertisements/{id}
 * Following paths will be available:
 *            api/{user_id}/advertisements
 *            api/{user_id}/advertisements/{id}
 *            api/advertisements/create
 *            api/{user_id}/update/{id}
 *            api/{user_id}/delete/{id}
 */
@RestController
public class MS2Controller {

  private IRepository inMemoryRepository;
  private Gson gson;

  public MS2Controller(){
    this.inMemoryRepository = InMemoryRepository.getInstance( new ArrayList<>() );
    this.gson = new Gson();
    Advertisement a1 = new Advertisement(2, "first",
        50, "computer nice", "tel. 473823748");
    Advertisement a2 = new Advertisement(2, "second",
        50, "computer nice", "tel. 473823748");
    inMemoryRepository.createAdvertisement(a1);
    inMemoryRepository.createAdvertisement(a2);
  }

  /**
   * root URI
   * @return test data
   */
  @RequestMapping("/")
  public String index() {
    return "Welcome to sellme! please proceed to http://10.101.104.9:8080/api/advertisements";
  }

  /**
   * API URI
   * @return message about the service
   */
  @RequestMapping("/api")
  public String api() {
    return "API for advertisement application";
  }

  /**
   * /api/advertisements URI to get all the advertisements as response in JSON format
   * @return JSON list of advertisements objects
   */
  @RequestMapping(value = "/api/advertisements", method = RequestMethod.GET, produces = "application/json")
  public String getAdvertisements() {
    return gson.toJson(inMemoryRepository.getAdvertisements());
  }

  /**
   * /api/advertisements/{id} URI to get specific advertisement as response in JSON format
   * @param id unique id of one specific advertisement
   * @return one specific advertisement object as JSON
   */
  // TODO add constraints, if object is available and error messages
  @RequestMapping(value = "/api/advertisements/{id}", method = RequestMethod.GET, produces = "application/json")
  public String getAdvertisementById (
      @PathVariable("id") int id
      )
  {
    return gson.toJson(inMemoryRepository.getAdvertisements().get(id));
  }

  @RequestMapping(value = "/api/advertisements/create", method = RequestMethod.POST)
  public String createAdvertisement (
      @RequestParam(value = "userId") int userId,
      @RequestParam(value = "token") String token
      )
  {

    if (isValid(token)) {
      Advertisement a3 = new Advertisement(userId, "second",
        50, "computer nice", "tel. 473823748");
      inMemoryRepository.createAdvertisement(a3);
    }

    return null;
  }

  public boolean isValid(String token){
    RestTemplate rt = new RestTemplate();
    try {
      return rt.getForObject(Environment.MS1+"/token/" + token, Boolean.class);
    } catch (NullPointerException e) {

    }
    return false;
  }

}