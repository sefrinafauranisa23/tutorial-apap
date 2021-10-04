package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.service.TravelAgensiService;
import apap.tutorial.pergipergi.service.TourGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;
import java.util.List;

@Controller
public class TourGuideController {
    @Qualifier("tourGuideServiceImpl")
    @Autowired
    private TourGuideService tourGuideService;

    @Qualifier("travelAgensiServiceImpl")
    @Autowired
    private TravelAgensiService travelAgensiService;

    @GetMapping("/tour-guide/add/{noAgensi}")
    public String addTourGuideFormPage(
            @PathVariable Long noAgensi,
            Model model) {
        TourGuideModel guide = new TourGuideModel();
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi != null) {
            guide.setAgensi(agensi);
            model.addAttribute("guide", guide);
            model.addAttribute("page", "add tour guide");
            return "form-add-tour-guide";
        } else {
            model.addAttribute("noAgensi", noAgensi);
            return "agensi-gagal";
        }
    }

    @PostMapping("/tour-guide/add")
    public String addTourGuideSubmitPage(
            @ModelAttribute TourGuideModel tourGuide,
            Model model
    ){
        List<TourGuideModel> list = tourGuide.getAgensi().getListTourGuide();
        for (TourGuideModel i : list) {
            if (i.getNamaTourGuide().equals(tourGuide.getNamaTourGuide())){
                model.addAttribute("proses", "Add Tour Guide");
                model.addAttribute("noAgensi", tourGuide.getAgensi().getNoAgensi());
                return "halaman-error";
            } else {
                continue;
            }
        }
        tourGuideService.addTourGuide(tourGuide);
        model.addAttribute("noTourGuide", tourGuide.getNoTourGuide());
        return "add-tour-guide";
    }

    @GetMapping("/tour-guide/delete/{noAgensi}/guide/{noTourGuide}")
    public String deleteTourGuide (
            @PathVariable Long noTourGuide,
            @PathVariable Long noAgensi,
            Model model
    ) {
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi != null) {
            List<TourGuideModel> tourGuide = agensi.getListTourGuide();
            List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();
            if (!(agensi.getWaktuBuka().compareTo(LocalTime.now()) < 0 && agensi.getWaktuTutup().compareTo(LocalTime.now()) >= 0)) {
                for (TourGuideModel tg : tourGuide) {
                    if (tg.getNoTourGuide().equals(noTourGuide)) {
                        agensi.getListTourGuide().remove(tg);
                        tourGuide.remove(tg);
                        travelAgensiService.updateAgensi(agensi);
                        tourGuideService.deleteTourGuide(tg);
                        model.addAttribute("noTourGuide", noTourGuide);
                        model.addAttribute("noAgensi", noAgensi);
                        model.addAttribute("proses", "dihapus");
                        return "proses-tour-guide";
                    }
                }
            }
            model.addAttribute("proses", "Delete Tour Guide");
            model.addAttribute("noAgensi", noAgensi);
            return "halaman-error";
        } else {
            model.addAttribute("noAgensi", noAgensi);
            return "agensi-gagal";
        }
    }

    @GetMapping("/tour-guide/update/{noAgensi}/guide/{noTourGuide}")
    public String updateTourGuide (
            @PathVariable Long noTourGuide,
            @PathVariable Long noAgensi,
            Model model
    ) {
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi != null) {
            List<TourGuideModel> tourGuide = agensi.getListTourGuide();
            if (!(agensi.getWaktuBuka().compareTo(LocalTime.now()) < 0 && agensi.getWaktuTutup().compareTo(LocalTime.now()) >= 0)) {
                for (TourGuideModel tg : tourGuide) {
                    if (tg.getNoTourGuide().equals(noTourGuide)) {
                        model.addAttribute("tourGuide", tg);
                        model.addAttribute("agensi", agensi);
                        return "form-update-tour-guide";
                    }
                }
            }
            model.addAttribute("proses", "Update Tour Guide");
            model.addAttribute("noAgensi", noAgensi);
            return "halaman-error";
        } else {
            model.addAttribute("noAgensi", noAgensi);
            return "agensi-gagal";
        }
    }

    @PostMapping("/tour-guide/update")
    public String updateTourGuideSubmitPage(
            @ModelAttribute TourGuideModel tourGuide,
            Model model
    ){
        TourGuideModel updatedTourGuide = tourGuideService.updateTourGuide(tourGuide);
        model.addAttribute("noTourGuide", updatedTourGuide.getNoTourGuide());
        model.addAttribute("noAgensi", updatedTourGuide.getAgensi().getNoAgensi());
        model.addAttribute("proses", "diubah");
        return "proses-tour-guide";
    }

    @PostMapping("/tour-guide/delete")
    public String deleteTourGuideSubmit(
            @ModelAttribute TravelAgensiModel agensi,
            Model model
    ){
        if (travelAgensiService.isClosed(agensi.getWaktuBuka(), agensi.getWaktuTutup())) {
            for (TourGuideModel tourGuide : agensi.getListTourGuide()) {
                tourGuideService.deleteTourGuide(tourGuide);
            }
            model.addAttribute("noAgensi", agensi.getNoAgensi());
            return "delete-tour-guide";
        }
        model.addAttribute("proses", "Delete Tour Guide");
        return "halaman-error";
    }
}

