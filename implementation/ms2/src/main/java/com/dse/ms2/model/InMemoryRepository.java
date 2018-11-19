package com.dse.ms2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * In memory repository singleton class to save advertisements.
 * A simple ArrayList is used.
 * Implements interface repository.
 */
public class InMemoryRepository implements IRepository {

  private List<Advertisement> advertisements;

  /**
   * Singleton pattern used.
   * @param advertisements list to instantiate InMemoryRepository
   */
  public InMemoryRepository(List<Advertisement> advertisements) {
    this.advertisements = advertisements;
    if (this.advertisements == null) {
      this.advertisements = new ArrayList<>();
    }
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
   * @param advId unique id of advertisement
   */
  @Override
  public void deleteAdvertisement(int advId) {

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
    return null;
  }

}
