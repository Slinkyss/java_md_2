package md.java_md2_d_kalnavs.Controller;

import jakarta.validation.Valid;
import md.java_md2_d_kalnavs.Models.AbstractCustomer;
import md.java_md2_d_kalnavs.Models.City;
import md.java_md2_d_kalnavs.Models.Driver;
import md.java_md2_d_kalnavs.Models.Parcel;
import md.java_md2_d_kalnavs.Services.IParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/parcels")
public class ParcelController {

    @Autowired
    private IParcelService parcelService;

    @GetMapping("/customer")
    public String showAllParcelsForCustomer(@RequestParam int id, Model model) {
        try {
            List<Parcel> parcels = parcelService.selectAllParcelsByCustomersId(id);
            model.addAttribute("parcels", parcels);
            model.addAttribute("price", id);
            model.addAttribute("title", "Customers parcels with id");
            return "parcel-all-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
    @GetMapping("/driver")
    public String showAllParcelsForDriver(@RequestParam int id, Model model) throws Exception {
        try {
        List<Parcel> parcels = parcelService.selectAllParcelsByDriversId(id);
        model.addAttribute("parcels", parcels);
        model.addAttribute("price", id);
            model.addAttribute("title", "Drivers parcels with id");
        return "parcel-all-page";
    } catch (Exception e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
    }

    @GetMapping("/price")
    public String showAllParcelsByPrice(@RequestParam int price, Model model) throws Exception {
        try {
            List<Parcel> parcels = parcelService.selectAllParcelsPriceLessThan(price);
            model.addAttribute("parcels", parcels);
            model.addAttribute("price", price);
            model.addAttribute("title", "Parcels less than");
            return "parcel-all-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
    @GetMapping("/city")
    public String showAllParcelsByCity(@RequestParam City city, Model model) throws Exception {
        try {
            List<Parcel> parcels = parcelService.selectAllParcelsByCity(city);
            model.addAttribute("parcels", parcels);
            model.addAttribute("price", city);
            model.addAttribute("title", "Parcels to");
            return "parcel-all-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    @GetMapping("/add")
    public String AddParcel(@RequestParam String personCode, Model model) {
        try {
            model.addAttribute("parcel", new Parcel());
            model.addAttribute("title", "Add Driver");
            model.addAttribute("personCode", personCode);
            return "parcel-add-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
    @PostMapping("/add")
    public String addParcel(@Valid Parcel parcel,String personCode, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Add Parcel");
            model.addAttribute("parcel", parcel);
            return "parcel-add-page";
        } else {
            try {

                parcelService.insertParcel(parcel.getSize(),parcel.isFragile(),parcel.getDriver().getId(), personCode);
                return "redirect:/";
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                return "error";
            }
        }
    }

    @GetMapping("/calculate")
    public String calculateParcel(@RequestParam int id, Model model) {
        try {
            model.addAttribute("id", id);
            model.addAttribute("title", "Calculate Parcel sum");
            model.addAttribute("sum",parcelService.calculateIncomeOfParcelByCustomerId(id));
            return "parcel-calculate-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/today")
    public String todayParcels(Model model) {
        try {
            model.addAttribute("title", "Today Parcels that need to be delivered");
            int i = parcelService.calculateHowManyParcelsNeedToDeliverToday();
            model.addAttribute("sum", i);
            return "parcel-calculate-page";


        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }

    }


}
