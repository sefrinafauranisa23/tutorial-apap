package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.TravelAgensiModel;

import java.util.List;

public interface TravelAgensiService {
    void addAgensi(TravelAgensiModel travelAgensi);
    void deleteAgensi(TravelAgensiModel travelAgensi);
    List<TravelAgensiModel> getListAgensi();
    TravelAgensiModel getAgensiByNoAgensi(Long noAgensi);
    TravelAgensiModel updateAgensi(TravelAgensiModel travelAgensi);
}

//@Service
//class TravelAgensiServiceImpl implements TravelAgensiService{
//    private List<TravelAgensiModel> listAgensi;
//
//    public TravelAgensiServiceImpl() {
//        listAgensi = new ArrayList<>();
//    }
//
//    @Override
//    public void addAgensi(TravelAgensiModel travelAgensiModel){
//        listAgensi.add(travelAgensiModel);
//    }
//
//    @Override
//    public List<TravelAgensiModel> getListAgensi() {
//        return listAgensi;
//    }
//
//    @Override
//    public TravelAgensiModel getAgensiByidAgensi(String idAgensi) {
//        for (TravelAgensiModel t : listAgensi) {
////            if (t.getIdAgensi().equals(idAgensi)) {
////                return t;
////            }
//        }
//        return null;
//    }
//}
