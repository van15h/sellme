package com.dse.ms2.model;

import com.dse.ms2.model.Advertisement;
import java.util.List;

public interface IRepository {

  void createAdvertisement(Advertisement advertisement);

  void deleteAdvertisement(int id);

  List<Advertisement> getAdvertisements();

  Advertisement getAdvertisementById(int id);

}
