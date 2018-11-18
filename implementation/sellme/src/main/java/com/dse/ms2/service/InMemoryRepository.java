package com.dse.ms2.service;

import com.dse.ms2.model.Advertisement;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements IRepository {

  private List<Advertisement> advertisements;

  public InMemoryRepository(List<Advertisement> advertisements) {
    this.advertisements = advertisements;
    if (this.advertisements == null) {
      this.advertisements = new ArrayList<>();
    }
  }

  @Override
  public void createAdvertisement(Advertisement advertisement) {
    advertisements.add(advertisement);
  }

  @Override
  public void deleteAdvertisement(String advId) {

  }

  @Override
  public List<Advertisement> getAdvertisements() {
    return advertisements;
  }

  @Override
  public Advertisement getAdvertisementById(int id) {
    return null;
  }

}
