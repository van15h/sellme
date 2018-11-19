package com.dse.ms2.model;

import java.util.List;

/**
 * Interface to define different repository strategies to save the objects on the server side.
 * Possible implementations: DBMS, Serialization, in-memory-repository.
 */
public interface IRepository {

  /**
   * Create new advertisement.
   * Is only possible for authenticated user.
   * @param advertisement new advertisement object
   */
  void createAdvertisement(Advertisement advertisement);

  /**
   * Delete user specific advertisement by id.
   * @param id unique id of advertisement
   */
  void deleteAdvertisement(int id);

  /**
   * Get the hole list of available advertisements
   * @return list of advertisements
   */
  List<Advertisement> getAdvertisements();

  /**
   * Get specific advertisement by id.
   * @param id unique id
   * @return one advertisement
   */
  Advertisement getAdvertisementById(int id);

}
