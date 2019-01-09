package com.dse.ms2.service;

import com.dse.ms2.model.Advertisement;
import com.dse.ms2.model.Environment;
import com.dse.ms2.model.IRepository;
import com.dse.ms2.model.InMemoryRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Controller class to handle all requests from the client.
 * Following endpoints are available:
 *            api/advertisements - list all advertisements
 *            api/advertisements/{id} - show specific advertisement
 *            api/user{user_id}/advertisements - list all advertisements of logged in user
 *            api/create - user creates advertisement
 *            api/update/{id} - user updates advertisement
 *            api/delete/{id} - user deletes advertisement
 */
@RestController
public class MS2Controller {

  private IRepository inMemoryRepository;
  private Gson gson;

  public MS2Controller(){
    this.inMemoryRepository = InMemoryRepository.getInstance( new ArrayList<>() );
    this.gson = new Gson();

    // dummy repository initialisation
    inMemoryRepository.createAdvertisement(new Advertisement(2, "first",
        50, "computer nice", "tel. 473823748"));
    inMemoryRepository.createAdvertisement(new Advertisement(1, "second",
        50, "computer nice", "tel. 473823748"));
  }

  /**
   * root URI
   * @return where to find the app
   */
  @RequestMapping("/")
  public String index() {
    return "Welcome to sellme! please proceed to http://10.101.104.9:8080/api/advertisements";
  }

  /**
   * API URI
   * @return message about API
   */
  @RequestMapping("/api")
  public String api() {
    return "API Version 1 for advertisement application. please proceed to http://10.101.104.9:8080/api/advertisements";
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
    return gson.toJson(inMemoryRepository.getAdvertisementById(id));
  }

  @RequestMapping(value = "/api/user{user_id}/advertisements", method = RequestMethod.GET, produces = "application/json")
  public String getAdvertisements(
      @PathVariable("userId") int userId
  ) {
    return gson.toJson(inMemoryRepository.getUserAdvertisements(userId));
  }

  /**
   * /api/create URI to create advertisement for valid user
   * @param userId
   * @param token
   * @param advertisement
   * @return
   */
  @RequestMapping(value = "/api/create", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity createAdvertisement (
      @RequestParam(value = "userId") int userId,
      @RequestParam(value = "token") String token,
      @RequestBody Advertisement advertisement
      )
  {
    if (isValid(token)) {
      //Advertisement a3 = new Advertisement(userId, "second", 50, "computer nice", "tel. 473823748");
      inMemoryRepository.createAdvertisement(advertisement);
      return new ResponseEntity(HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }

  @RequestMapping(value = "/api/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody ResponseEntity deleteAdvertisement (
      @PathVariable("id") int id,
      @RequestParam(value = "token") String token
  )
  {
    if (isValid(token)) {
      if (userHasAdvertisement(id)) {
        inMemoryRepository.deleteAdvertisement(id);
        return new ResponseEntity(HttpStatus.OK);
      }
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }

  @RequestMapping(value = "/api/update/{id}", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity updateAdvertisement (
      @PathVariable("id") int id,
      @RequestParam(value = "token") String token,
      @RequestBody Advertisement advertisement
  )
  {
    if (isValid(token)) {
      if (userHasAdvertisement(id)) {
        inMemoryRepository.updateAdvertisement(advertisement);
        return new ResponseEntity(HttpStatus.OK);
      }
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }

  private boolean isValid(String token){
    RestTemplate rt = new RestTemplate();
    return rt.getForObject(Environment.MS1+"/token/" + token, Boolean.class);
  }

  private boolean userHasAdvertisement(int id){
    for (Advertisement adv:
        inMemoryRepository.getAdvertisements()) {
      if (adv.getId() == id)
        return true;
    }
    return false;
  }

}