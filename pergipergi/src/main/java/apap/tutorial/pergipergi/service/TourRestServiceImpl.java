package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.AgifyModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.repository.DestinasiDB;
import apap.tutorial.pergipergi.repository.TourGuideDb;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class TourRestServiceImpl implements TourRestService {

    @Autowired
    private TourGuideDb tourGuideDb;

    private final WebClient webClient;

    @Override
    public TourGuideModel getTourGuideByNoTourGuide(Long noTourGuide) {
        Optional<TourGuideModel> tourGuide = tourGuideDb.findByNoTourGuide(noTourGuide);

        if(tourGuide.isPresent()) {
            return tourGuide.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public TourGuideModel updateTourGuide(Long noTourGuide, TourGuideModel tourGuideUpdate) {
        TourGuideModel tourGuide = getTourGuideByNoTourGuide(noTourGuide);
        tourGuide.setNamaTourGuide(tourGuideUpdate.getNamaTourGuide());
        tourGuide.setJenisKelamin(tourGuideUpdate.getJenisKelamin());
        tourGuide.setUmur(tourGuideUpdate.getUmur());

        return tourGuideDb.save(tourGuide);
    }

    public TourRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.umurUrl).build();
    }

    @Override
    public AgifyModel getStatus(Long noTourGuide) {
        Optional<TourGuideModel> tourGuide = tourGuideDb.findByNoTourGuide(noTourGuide);
        return this.webClient.get().uri("?name=" + tourGuide.get().getNamaTourGuide())
                .retrieve()
                .bodyToMono(AgifyModel.class).block();
    }
}
