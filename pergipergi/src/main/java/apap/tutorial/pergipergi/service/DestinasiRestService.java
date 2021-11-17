package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.rest.DestinasiDetail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DestinasiRestService {
    DestinasiModel createDestinasi(DestinasiModel agensi);
    List<DestinasiModel> retrieveListDestinasi();
    DestinasiModel getDestinasiByNoDestinasi(Long noDestinasi);
    DestinasiModel updateDestinasi(Long noDestinasi, DestinasiModel destinasiUpdate);
    void deleteDestinasi(Long noDestinasi);
//    Mono<String> getStatus(Long noDestinasi);
//    Mono<DestinasiDetail> postStatus();
}
