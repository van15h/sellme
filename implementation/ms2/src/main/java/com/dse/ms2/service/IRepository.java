package com.dse.ms2.service;

import com.dse.ms2.model.Advertisement;
import java.util.List;

public interface IRepository {

  void createAdvertisement(Advertisement advertisement);

  void deleteAdvertisement(String id);

  List<Advertisement> getAdvertisements();

  Advertisement getAdvertisementById(int id);

}
