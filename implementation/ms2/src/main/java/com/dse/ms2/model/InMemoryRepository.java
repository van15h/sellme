package com.dse.ms2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * In memory repository singleton class to save advertisements.
 * A simple ArrayList is used.
 * Implements interface repository.
 */
public class InMemoryRepository implements IRepository {

  static int currentAdvId = 0;
  private List<Advertisement> advertisements;

  // singleton pattern
  private static InMemoryRepository instance = null;

  /**
   * Singleton pattern used.
   * @param advertisements list to instantiate InMemoryRepository
   */
  private InMemoryRepository(List<Advertisement> advertisements) {
    this.advertisements = advertisements;
    if (this.advertisements == null) {
      this.advertisements = new ArrayList<>();
    }
  }

  public static InMemoryRepository getInstance() {
    if(instance == null) {
      instance = new InMemoryRepository(new ArrayList<>());
    }
    return instance;
  }

  /**
   * Create new advertisement.
   * Is only possible for authenticated user.
   * @param advertisement new advertisement object
   */
  @Override
  public void createAdvertisement(Advertisement advertisement) {
    advertisements.add(advertisement);
  }

  /**
   * Delete user specific advertisement by id.
   * @param id unique id of advertisement
   */
  @Override
  public void deleteAdvertisement(int id) {
    for (Advertisement adv: advertisements) {
      if (adv.getId() == id) {
        advertisements.remove(adv);
        break;
      }
    }
  }

  /**
   * Get the hole list of available advertisements
   * @return list of advertisements
   */
  @Override
  public List<Advertisement> getAdvertisements() {
    return advertisements;
  }

  /**
   * Get specific advertisement by id.
   * @param id unique id
   * @return one advertisement
   */
  @Override
  public Advertisement getAdvertisementById(int id) {
    for (Advertisement adv:
        advertisements ) {
      if (adv.getId() == id) {
        return adv;
      }
    }
    return null;
  }

  @Override
  public List<Advertisement> getUserAdvertisements(int userId) {
    List<Advertisement> userAdvertisements = new ArrayList<>();
    for (Advertisement adv:
        advertisements) {
      if (adv.getUserId() == userId) {
        userAdvertisements.add(adv);
      }
    }
    return userAdvertisements;
  }

  @Override
  public void updateAdvertisement(Advertisement update) {
    for (Advertisement adv :
        advertisements) {
      if (adv.getId() == update.getId()) {
        adv.setSubject(update.getSubject());
        adv.setDescription(update.getDescription());
        adv.setPrice(update.getPrice());
        adv.setContactInfo(update.getContactInfo());
        break;
      }
    }
  }


}
