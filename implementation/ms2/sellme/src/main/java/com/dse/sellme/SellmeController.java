package com.dse.sellme;

import com.dse.sellme.model.Advertisement;
import com.dse.sellme.service.IRepository;
import com.dse.sellme.service.InMemoryRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SellmeController {

  private IRepository inMemoryRepository;
  Gson gson;

  @RequestMapping("/")
  public String index() {
    return "Privet Hamlet ti voobsche Krutoi!";
  }

  @RequestMapping("/api")
  public String api() {
    return "Api for Sellme application";
  }

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

  @RequestMapping(value = "/api/advertisements/{id}", method = RequestMethod.GET, produces = "application/json")
  public String getAdvertisementById (@PathVariable("id") int id)
  {
    return gson.toJson(inMemoryRepository.getAdvertisements().get(id));
  }

  public boolean checkToken(){return true;}

}