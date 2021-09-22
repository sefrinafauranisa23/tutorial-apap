package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.repository.TourGuideDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TourGuideServiceImpl implements TourGuideService {

    @Autowired
    TourGuideDb tourGuideDb;

    @Override
    public void addTourGuide(TourGuideModel tourGuide) {
        tourGuideDb.save(tourGuide);
    }

    @Override
    public void deleteTourGuide(TourGuideModel tourGuide) {
        tourGuideDb.delete(tourGuide);
    }

    @Override
    public TourGuideModel updateTourGuide(TourGuideModel tourGuide) {
        tourGuideDb.save(tourGuide);
        return tourGuide;
    }
}
