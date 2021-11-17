package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.repository.DestinasiDB;
import apap.tutorial.pergipergi.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Transactional


public class DestinasiRestServiceImpl implements DestinasiRestService{

    @Autowired
    private DestinasiDB destinasiDB;

//    private final WebClient webClient;

    @Override
    public DestinasiModel createDestinasi(DestinasiModel destinasi) {
        return destinasiDB.save(destinasi);
    }

    @Override
    public List<DestinasiModel> retrieveListDestinasi() {
        return destinasiDB.findAll();
    }


    @Override
    public DestinasiModel getDestinasiByNoDestinasi(Long noDestinasi){
        Optional<DestinasiModel> destinasi = destinasiDB.findByNoDestinasi(noDestinasi);

        if(destinasi.isPresent()) {
            return destinasi.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public DestinasiModel updateDestinasi(Long noDestinasi, DestinasiModel DestinasiUpdate) {
        DestinasiModel destinasi = getDestinasiByNoDestinasi(noDestinasi);
        destinasi.setNegaraDestinasi(DestinasiUpdate.getNegaraDestinasi());
        destinasi.setIsBebasVisa(DestinasiUpdate.getIsBebasVisa());

        return destinasiDB.save(destinasi);
    }

    @Override
    public void deleteDestinasi(Long noDestinasi){
        DestinasiModel Agensi = getDestinasiByNoDestinasi(noDestinasi);
        destinasiDB.delete(Agensi);
    }

//    public DestinasiRestServiceImpl(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl(Setting.destinasiURL).build();
//    }

//    @Override
//    public Mono<String> getStatus(Long noAgensi) {
//        return this.webClient.get().uri("/rest/agensi/" + noAgensi + "/status")
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//
//    @Override
//    public Mono<DestinasiDetail> postStatus() {
//        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
//        data.add("namaAgensi", "Agensi Mock Server");
//        data.add("alamatAgensi", "Depok");
//        return this.webClient.post().uri("/rest/agensi/full")
//                .syncBody(data)
//                .retrieve()
//                .bodyToMono(DestinasiDetail.class);
//    }
}

