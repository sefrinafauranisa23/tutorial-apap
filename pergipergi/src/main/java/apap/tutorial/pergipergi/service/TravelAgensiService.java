package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

public interface TravelAgensiService {
    // Method untuk menambahkan agensi
    void addAgensi(TravelAgensiModel travelAgensi);

    // Method untuk mendapatkan daftar agensi yang telah tersimpan
    List<TravelAgensiModel> getListAgensi();

    // Method untuk mendapatkan data agensi berdasarkan id agensi
    TravelAgensiModel getAgensiByidAgensi(String idAgensi);
}

@Service
class TravelAgensiServiceImpl implements TravelAgensiService{
    private List<TravelAgensiModel> listAgensi;

    public TravelAgensiServiceImpl() {
        listAgensi = new ArrayList<>();
    }

    @Override
    public void addAgensi(TravelAgensiModel travelAgensiModel){
        listAgensi.add(travelAgensiModel);
    }

    @Override
    public List<TravelAgensiModel> getListAgensi() {
        return listAgensi;
    }

    @Override
    public TravelAgensiModel getAgensiByidAgensi(String idAgensi) {
        for (TravelAgensiModel t : listAgensi) {
            if (t.getIdAgensi().equals(idAgensi)) {
                return t;
            }
        }
        return null;
    }
}
