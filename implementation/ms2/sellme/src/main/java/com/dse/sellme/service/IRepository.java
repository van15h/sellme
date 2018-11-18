package com.dse.sellme.service;

import com.dse.sellme.model.Advertisement;
import java.util.List;

public interface IRepository {

  void createAdvertisement(Advertisement advertisement);

  void deleteAdvertisement(String id);

  List<Advertisement> getAdvertisements();

  Advertisement getAdvertisementById(int id);

}
