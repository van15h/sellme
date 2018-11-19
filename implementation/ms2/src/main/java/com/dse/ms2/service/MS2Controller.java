package com.dse.ms2.service;

import com.dse.ms2.model.Advertisement;
import com.dse.ms2.model.IRepository;
import com.dse.ms2.model.InMemoryRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class to handle all requests from the client.
 * Following paths are available:
 *            api/advertisements
 *            api/advertisements/{id}
 * Following paths will be available:
 *            api/{user_id}/advertisements
 *            api/{user_id}/advertisements/{id}
 *            api/{user_id}/create
 *            api/{user_id}/update/{id}
 *            api/{user_id}/delete/{id}
 */
@RestController
public class MS2Controller {

  private IRepository inMemoryRepository;
  private Gson gson;

  /**
   * root URI
   * @return test data
   */
  @RequestMapping("/")
  public String index() {
    return "Privet Hamlet ti voobsche Krutoi!";
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
    inMemoryRepository = new InMemoryRepository( new ArrayList<>() );
    gson = new Gson();

    Advertisement a1 = new Advertisement(0,2, "first",
        50, "computer nice", "tel. 473823748");
    Advertisement a2 = new Advertisement(1,2, "second",
        50, "computer nice", "tel. 473823748");

    inMemoryRepository.createAdvertisement(a1);
    inMemoryRepository.createAdvertisement(a2);
    return gson.toJson(inMemoryRepository.getAdvertisements());
  }

  /**
   * /api/advertisements/{id} URI to get specific advertisement as response in JSON format
   * @param id unique id of one specific advertisement
   * @return one specific advertisement object as JSON
   */
  // TODO add constraints, if object is available and error messages
  @RequestMapping(value = "/api/advertisements/{id}", method = RequestMethod.GET, produces = "application/json")
  public String getAdvertisementById (@PathVariable("id") int id)
  {
    return gson.toJson(inMemoryRepository.getAdvertisements().get(id));
  }

  public boolean checkToken(String token){return true;}

}