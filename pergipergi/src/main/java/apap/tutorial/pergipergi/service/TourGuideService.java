package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;

import java.util.List;

public interface TourGuideService {
    void addTourGuide(TourGuideModel tourGuide);
    void deleteTourGuide(TourGuideModel tourGuide);
    TourGuideModel updateTourGuide(TourGuideModel tourGuide);
    List<TourGuideModel> getListTourGuide();
}
