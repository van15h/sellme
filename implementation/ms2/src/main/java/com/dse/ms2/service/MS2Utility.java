package com.dse.ms2.service;

import com.dse.ms2.model.Advertisement;
import com.dse.ms2.model.Environment;
import com.dse.ms2.model.IRepository;
import com.dse.ms2.model.InMemoryRepository;
import org.springframework.web.client.RestTemplate;

/**
 * Utility class that provides:
 * repository initialization
 * session token validation
 * checked if user has rights to modify advertisement
 */
class MS2Utility {

  private IRepository inMemoryRepository;

  void intializeRepository(){
    this.inMemoryRepository = InMemoryRepository.getInstance();

    // dummy repository initialisation
    inMemoryRepository.createAdvertisement(new Advertisement(2, "first",
        50, "computer nice", "tel. 473823748"));
    inMemoryRepository.createAdvertisement(new Advertisement(1, "second",
        50, "computer very nice", "tel. 36736427"));
    inMemoryRepository.createAdvertisement(new Advertisement(2, "third",
        50, "computer bad", "tel. 473823748"));
    inMemoryRepository.createAdvertisement(new Advertisement(1, "fourth",
        50, "computer old", "tel. 36736427"));
  }

  boolean isValid(String token){
    RestTemplate rt = new RestTemplate();
    return rt.getForObject(Environment.MS1+"/token/" + token, Boolean.class);
  }

  boolean userHasAdvertisement(int id){
    for (Advertisement adv:
        inMemoryRepository.getAdvertisements()) {
      if (adv.getId() == id) {
        return true;
      }
    }
    return false;
  }

}
