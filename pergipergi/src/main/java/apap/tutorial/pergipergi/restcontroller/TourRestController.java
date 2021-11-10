package apap.tutorial.pergipergi.restcontroller;

import apap.tutorial.pergipergi.model.AgifyModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.repository.TourGuideDb;
import apap.tutorial.pergipergi.service.TourRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class TourRestController {

    @Autowired
    private TourRestService tourRestService;

//    @GetMapping(value="/tour/umur/{noTourGuide}")
//    private TourGuideModel umurTourGuide(@PathVariable("noTourGuide") Long noTourGuide) {
//        try {
//            TourGuideModel tourGuideModel = tourRestService.getTourGuideByNoTourGuide(noTourGuide);
//            String name = tourGuideModel.getNamaTourGuide();
//            String url = "https://api.agify.io?name="+name;
//            RestTemplate restTemplate = new RestTemplate();
//            HashMap objek = restTemplate.getForObject(url, HashMap.class);
////            System.out.println(objek);
//            Object[] key = objek.keySet().toArray();
//            String umur = "";
//            for (Object a : key) {
//                if (a.toString().equalsIgnoreCase("age")) {
//                    umur = objek.get(a).toString();
//                }
//            }
//            tourRestService.getTourGuideByNoTourGuide(noTourGuide).setUmur(umur);
////            System.out.println( tourRestService.getTourGuideByNoTourGuide(noTourGuide).getUmur());
//            return tourRestService.updateTourGuide(noTourGuide, tourRestService.getTourGuideByNoTourGuide(noTourGuide));
//
//        } catch (NoSuchElementException e) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "No Tour Guide " + String.valueOf(noTourGuide) + " Not Found."
//            );
//        }
//    }

    @GetMapping(value="/tour/umur/{noTourGuide}")
    private TourGuideModel umurTourGuide(@PathVariable("noTourGuide") Long noTourGuide) {
        try {
            TourGuideModel tourGuideModel = tourRestService.getTourGuideByNoTourGuide(noTourGuide);
            AgifyModel agify = tourRestService.getStatus(noTourGuide);
            String umur = agify.age.toString();
            tourRestService.getTourGuideByNoTourGuide(noTourGuide).setUmur(umur);
            return tourRestService.updateTourGuide(noTourGuide, tourRestService.getTourGuideByNoTourGuide(noTourGuide));

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Tour Guide " + String.valueOf(noTourGuide) + " Not Found."
            );
        }
    }
}
