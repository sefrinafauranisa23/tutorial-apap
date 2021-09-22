package apap.tutorial.pergipergi.controller;

import apap.tutorial.pergipergi.model.TravelAgensiModel;
import apap.tutorial.pergipergi.service.TravelAgensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TravelAgensiController {
    @Autowired
    private TravelAgensiService travelAgensiService;

    // Routing URL yang diinginkan
    @RequestMapping("agensi/add")
    public String addAgensi(
            @RequestParam(value = "idAgensi", required = true) String idAgensi,
            @RequestParam(value = "namaAgensi", required = true) String namaAgensi,
            @RequestParam(value = "alamat", required = true) String alamat,
            @RequestParam(value = "noTelepon", required = true) String noTelepon,
            Model model
    ) {

        // Membuat Objek TravelAgensiModel
        TravelAgensiModel agensi = new TravelAgensiModel(idAgensi, namaAgensi, alamat, noTelepon);

        // Memanggil Servis addAgensi
        travelAgensiService.addAgensi(agensi);

        // Add variabel id agensi ke 'idAgensi' untuk dirender di thymeleaf
        model.addAttribute("idAgensi", idAgensi);

        // return view template yang digunakan
        return "add-agensi";
    }

    @RequestMapping("agensi/viewAll")
    public String listAgensi(Model model) {
        // Mendapatkan semua TravelAgensiModel
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();

        // Add variabel semua TravelAgensiModel ke "ListAgensi" untuk dirender pada thymeleaf
        model.addAttribute("listAgensi", listAgensi);

        // Return view template yang diinginkan
        return "viewall-agensi";
    }

    @RequestMapping("agensi/view")
    public String detailAgensi (
            @RequestParam(value="idAgensi") String idAgensi,
            Model model
    ){
        // Mendapatkan TravelAgensiModel sesuai dengan idAgensi
        TravelAgensiModel agensi = travelAgensiService.getAgensiByidAgensi(idAgensi);

        // Jika Agensi tidak ditemukan
        if (agensi == null) {
            model.addAttribute("proses", "view");
            model.addAttribute("idAgensi", idAgensi);
            return "halaman-error";

            // Jika Agensi ditemukan
        } else {
            // Add variabel TravelAgensiModel ke "agensi" untuk dirender pada thymeleaf
            model.addAttribute("agensi", agensi);
            return "view-agensi";
        }
    }

    @GetMapping(value="/agensi/view/id-agensi/{idAgensi}")
    public String detailAgensiPV (
            @PathVariable(value="idAgensi") String idAgensi,
            Model model){

        // Mendapatkan TravelAgensiModel sesuai dengan idAgensi
        TravelAgensiModel agensi = travelAgensiService.getAgensiByidAgensi(idAgensi);

        // Jika Agensi tidak ditemukan
        if (agensi == null) {
            model.addAttribute("proses", "view");
            model.addAttribute("idAgensi", idAgensi);
            return "halaman-error";

            // Jika Agensi ditemukan
        } else {
            // Add variabel TravelAgensiModel ke "agensi" untuk dirender pada thymeleaf
            model.addAttribute("agensi", agensi);
            return "view-agensi";
        }
    }

    @GetMapping(value="/agensi/update/id-agensi/{idAgensi}/no-telepon/{noTelepon}")
    public String updateAgensi (
            @PathVariable(value="idAgensi") String idAgensi,
            @PathVariable(value="noTelepon") String noTelepon,
            Model model){

        // Mendapatkan TravelAgensiModel sesuai dengan idAgensi
        TravelAgensiModel agensi = travelAgensiService.getAgensiByidAgensi(idAgensi);

        // Jika Agensi tidak ditemukan
        if (agensi == null) {
            model.addAttribute("proses", "update");
            model.addAttribute("idAgensi", idAgensi);
            return "halaman-error";

            // Jika Agensi ditemukan
        } else {
            agensi.setNoTelepon(noTelepon);
            model.addAttribute("idAgensi", idAgensi);
            model.addAttribute("berhasil", "ubah");
            return "proses-berhasil";
        }
    }

    @GetMapping(value="/agensi/delete/id-agensi/{idAgensi}")
    public String deleteAgensi (
            @PathVariable(value="idAgensi") String idAgensi,
            Model model){

        // Mendapatkan semua TravelAgensiModel
        List<TravelAgensiModel> listAgensi = travelAgensiService.getListAgensi();

        // Mendapatkan TravelAgensiModel sesuai dengan idAgensi
        TravelAgensiModel agensi = travelAgensiService.getAgensiByidAgensi(idAgensi);

        // Jika Agensi tidak ditemukan
        if (agensi == null) {
            model.addAttribute("proses", "delete");
            model.addAttribute("idAgensi", idAgensi);
            return "halaman-error";

            // Jika Agensi ditemukan
        } else {
            listAgensi.remove(agensi);
            model.addAttribute("idAgensi", idAgensi);
            model.addAttribute("berhasil", "hapus");
            return "proses-berhasil";
        }
    }
}
