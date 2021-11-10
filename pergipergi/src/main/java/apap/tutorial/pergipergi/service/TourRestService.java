package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.AgifyModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.rest.AgensiDetail;
import apap.tutorial.pergipergi.rest.DestinasiDetail;
import reactor.core.publisher.Mono;
import java.util.List;

public interface TourRestService {
    TourGuideModel getTourGuideByNoTourGuide(Long noTourGuide);
    TourGuideModel updateTourGuide(Long noTourGuide, TourGuideModel tourGuideUpdate);
    AgifyModel getStatus(Long noTourGuide);
//    Mono<AgensiDetail> postStatus();
}
