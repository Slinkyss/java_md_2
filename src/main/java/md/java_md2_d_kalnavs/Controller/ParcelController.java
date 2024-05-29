package md.java_md2_d_kalnavs.Controller;

import md.java_md2_d_kalnavs.Models.Parcel;
import md.java_md2_d_kalnavs.Services.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequestMapping("/parcels")
public class ParcelController {

    @Autowired
    IParcelService parcelService;

    @GetMapping("/customer")
    public String showAllParcels(@RequestParam int id, Model model) {
        try {
            List<Parcel> parcels = parcelService.selectAllParcelsByCustomersId(id);
            model.addAttribute("parcels", parcels);
            model.addAttribute("id", id);
            return "parcel-all-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }




}

