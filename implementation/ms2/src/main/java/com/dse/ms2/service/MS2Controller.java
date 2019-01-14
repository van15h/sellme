package com.dse.ms2.service;

import com.dse.ms2.model.Advertisement;
import com.dse.ms2.model.IRepository;
import com.dse.ms2.model.InMemoryRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
  private MS2Utility utility;
//  private Gson gson;

  public MS2Controller(){
    this.inMemoryRepository = InMemoryRepository.getInstance();
    this.utility = new MS2Utility();
    this.utility.intializeRepository();
//    this.gson = new Gson();
  }

  /**
   * root URI
   * @return where to find the app
   */
  @RequestMapping("/")
  public String index() {
    return "Welcome to sellme! \n please proceed to http://10.101.104.9:8080/api/advertisements";
  }

  /**
   * API URI
   * @return message about API
   */
  @RequestMapping("/api")
  public String api() {
    return "API Version 1 for advertisement application. \n please proceed to http://10.101.104.9:8080/api/advertisements";
  }

  /**
   * /api/advertisements URI to get all the advertisements as response in JSON format
   * @return JSON list of advertisements objects
   */
  @RequestMapping(value = "/api/advertisements", method = RequestMethod.GET)
  public List<Advertisement> getAdvertisements() {

    return inMemoryRepository.getAdvertisements();
  }

  /**
   * /api/advertisements/{id} URI to get specific advertisement as response in JSON format
   * @param id unique id of one specific advertisement
   * @return one specific advertisement object as JSON
   */
  @RequestMapping(value = "/api/advertisements/{id}", method = RequestMethod.GET)
  public Advertisement getAdvertisementById ( @PathVariable("id") int id ) {
    return inMemoryRepository.getAdvertisementById(id);
  }

  @RequestMapping(value = "/api/user{user_id}/advertisements", method = RequestMethod.GET)
  public List<Advertisement> getAdvertisements( @PathVariable("user_id") String userId ) {
    return inMemoryRepository.getUserAdvertisements(userId);
  }

  /**
   * /api/create URI to create advertisement for valid user
   * @param userId user id
   * @param token user token to check session
   * @param advertisement object to create
   * @return status code of the operation
   */
  @RequestMapping(value = "/api/create", method = RequestMethod.POST, consumes = "application/json")
  public @ResponseBody ResponseEntity createAdvertisement (
      @RequestParam(value = "userId") String userId,
      @RequestParam(value = "token") String token,
      @RequestBody Advertisement advertisement
      )
  {
    if (utility.isValid(token)) {
      advertisement.setId(++InMemoryRepository.currentAdvId);
      inMemoryRepository.createAdvertisement(advertisement);
      return new ResponseEntity(HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.FORBIDDEN);
  }

  /**
   * /api/delete/{id} URI to delete custom advertisement
   * @param id unique id if advertisement
   * @param token user token to check session time
   * @return status codes of the operation
   */
  @RequestMapping(value = "/api/delete/{id}", method = RequestMethod.DELETE)
  public @ResponseBody ResponseEntity deleteAdvertisement (
      @PathVariable("id") int id,
      @RequestParam(value = "token") String token
  )
  {
    if (utility.isValid(token)) {
      if (utility.userHasAdvertisement(id)) {
        inMemoryRepository.deleteAdvertisement(id);
        return new ResponseEntity(HttpStatus.OK);
      }
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(HttpStatus.FORBIDDEN);
  }

  /**
   * /api/update/{id} URI for updating advertisement
   * @param id of advertisement
   * @param token of user to check session time
   * @param advertisement object for updating existing one
   * @return status code of the operation
   */
  @RequestMapping(value = "/api/update/{id}", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity updateAdvertisement (
      @PathVariable("id") int id,
      @RequestParam(value = "token") String token,
      @RequestBody Advertisement advertisement
  )
  {
    if (utility.isValid(token)) {
      if (utility.userHasAdvertisement(id)) {
        inMemoryRepository.updateAdvertisement(advertisement);
        return new ResponseEntity(HttpStatus.OK);
      }
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(HttpStatus.FORBIDDEN);
  }

}