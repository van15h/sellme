package com.dse.ms2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * In memory repository singleton class to save advertisements.
 * A simple ArrayList is used.
 * Implements interface repository.
 */
public class InMemoryRepository implements IRepository {

  public static int currentAdvId = 0;
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

  public static InMemoryRepository getInstance(List<Advertisement> advertisements) {
    if(instance == null) {
      instance = new InMemoryRepository(advertisements);
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
