package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.DestinasiModel;
import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.model.TourGuideModel;
import apap.tutorial.pergipergi.service.DestinasiService;
import apap.tutorial.pergipergi.service.TravelAgensiService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TravelAgensiController<params> {

    @Qualifier("travelAgensiServiceImpl")
    @Autowired
    private TravelAgensiService travelAgensiService;

    @Qualifier("destinasiServiceImpl")
    @Autowired
    DestinasiService destinasiService;

    @GetMapping("/agensi/add")
    public String addAgensiFormPage(Model model) {
        List<DestinasiModel> listDestinasi = destinasiService.getListDestinasi();
        model.addAttribute("agensi", new TravelAgensiModel());
        model.addAttribute("listDestinasi", listDestinasi);
        model.addAttribute("page", "add agensi");
        return "form-add-agensi";
    }

    @PostMapping(value="/agensi/add", params =("save"))
    public String addAgensiSubmitPage(
            @ModelAttribute TravelAgensiModel agensi,
            Model model
    ) {
        travelAgensiService.addAgensi(agensi);
        model.addAttribute("noAgensi", agensi.getNoAgensi());
        return "add-agensi";
    }

//    @PostMapping(value = "/agensi/add", params=("addRow"))
//    public String addRow(
//            @ModelAttribute TravelAgensiModel agensi,
//            Model model
//    ) {
//        if (agensi.getListDestinasi() == null || agensi.getListDestinasi().size() == 0) {
//            agensi.setListDestinasi(new ArrayList<>());
//        }
//        agensi.getListDestinasi().add(new DestinasiModel());
//        model.addAttribute("agensi", agensi);
//        model.addAttribute("listDestinasiExisting", destinasiService.getListDestinasi());
//        return "form-add-agensi";
//    }

    @PostMapping(value = "/agensi/add", params=("addRow"))
    public String addRow(
            final TravelAgensiModel agensi,
            final BindingResult bindingResult,
            Model model
    ) {
        if (agensi.getListDestinasi() == null || agensi.getListDestinasi().size() == 0) {
            agensi.setListDestinasi(new ArrayList<>());
        }
        agensi.getListDestinasi().add(new DestinasiModel());
        model.addAttribute("agensi", agensi);
        model.addAttribute("listDestinasiExisting", destinasiService.getListDestinasi());
        return "form-add-agensi";
    }

//    @PostMapping(value="/agensi/add", params=("deleteRow"))
//    public String deleteRow(
//            @ModelAttribute TravelAgensiModel agensi,
//            Model model
//    ) {
//        agensi.getListDestinasi().remove(0);
//        model.addAttribute("agensi", agensi);
//        model.addAttribute("listDestinasiExisting", destinasiService.getListDestinasi());
//        return "form-add-agensi";
//    }

    @PostMapping(value="/agensi/add", params=("deleteRow"))
    public String deleteRow(
            final TravelAgensiModel agensi,
            final BindingResult bindingResult,
            final HttpServletRequest req,
            Model model
    ) {
        final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
        agensi.getListDestinasi().remove(rowId.intValue());
        model.addAttribute("agensi", agensi);
        model.addAttribute("listDestinasiExisting", destinasiService.getListDestinasi());
        return "form-add-agensi";
    }

    @GetMapping("/agensi/viewall")
    public String listAgensi(Model model) {
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();
        model.addAttribute("listAgensi", listAgensi);
        model.addAttribute("page", "view all agensi");
        return "viewall-agensi";
    }

    @GetMapping("/agensi/view")
    public String viewDetailAgensiPage(
            @RequestParam(value = "noAgensi") Long noAgensi,
            Model model
    ) {
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi != null) {
            List<TourGuideModel> listTourGuide = agensi.getListTourGuide();
            List<DestinasiModel> listDestinasi = agensi.getListDestinasi();
            model.addAttribute("agensi", agensi);
            model.addAttribute("listTourGuide", listTourGuide);
            model.addAttribute("listDestinasi", listDestinasi);
            model.addAttribute("page", "view agensi");
            return "view-agensi";
        } else {
            model.addAttribute("noAgensi", noAgensi);
            return "agensi-gagal";
        }
    }

    @GetMapping("/agensi/update/{noAgensi}")
    public String updateAgensiFormPage (
            @PathVariable Long noAgensi,
            Model model
    ) {
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi != null) {
            model.addAttribute("agensi", agensi);
            model.addAttribute("page", "update agensi");
            return "form-update-agensi";
        } else {
            model.addAttribute("noAgensi", noAgensi);
            return "agensi-gagal";
        }
    }

    @PostMapping("/agensi/update")
    public String updateAgensiSubmitPage(
            @ModelAttribute TravelAgensiModel agensi,
            Model model
    ){
        TravelAgensiModel updatedAgensi = travelAgensiService.updateAgensi(agensi);
        model.addAttribute("noAgensi", updatedAgensi.getNoAgensi());
        return "update-agensi";
    }

    @GetMapping("/agensi/delete/{noAgensi}")
    public String deleteAgensi (
            @PathVariable Long noAgensi,
            Model model
    ) {
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();
        TravelAgensiModel agensi = travelAgensiService.getAgensiByNoAgensi(noAgensi);
        if (agensi != null) {
            if (agensi.getListTourGuide().isEmpty()) {
                if (!(agensi.getWaktuBuka().compareTo(LocalTime.now()) < 0 && agensi.getWaktuTutup().compareTo(LocalTime.now()) >= 0)) {
                    listAgensi.remove(agensi);
                    travelAgensiService.deleteAgensi(agensi);
                    model.addAttribute("proses", "dihapus");
                    model.addAttribute("noAgensi", noAgensi);
                    return "proses-berhasil";
                }
            }
            model.addAttribute("proses", "Delete Agensi");
            model.addAttribute("noAgensi", noAgensi);
            return "halaman-error";
        } else {
            model.addAttribute("noAgensi", noAgensi);
            return "agensi-gagal";
        }
    }
}

