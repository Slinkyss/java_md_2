package md.java_md2_d_kalnavs.Controller;


import jakarta.validation.Valid;
import md.java_md2_d_kalnavs.Models.Driver;
import md.java_md2_d_kalnavs.Services.IDriverCRUDService;
import md.java_md2_d_kalnavs.repo.IDriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private IDriverCRUDService driverCRUDService;



    @GetMapping("/show/all")
    public String getAllDrivers(Model model) {
        try {
            model.addAttribute("drivers", driverCRUDService.getAllDrivers());
            model.addAttribute("title", "All Drivers");
            return "all-drivers";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/driver")
    public String getDriver(Model model, @RequestParam int id) {
        try {
            model.addAttribute("driver", driverCRUDService.getDriverById(id));
            model.addAttribute("title", "Driver details");
            return "driver-one-page";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    @GetMapping("/remove")
    public String removeDriver(Model model, @RequestParam int id) {
        try {
            driverCRUDService.deleteDriver(id);

            return  "redirect:/driver/show/all";

        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/add")
    public String getAdDriver(Model model) {
        try {
            model.addAttribute("driver", new Driver());
            model.addAttribute("title", "Add Driver");
            return "driver-add-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/add")
    public String addDriver(@Valid Driver driver, BindingResult result) {
        if (result.hasErrors()) {
            return "driver-add-page";
        } else {
            try {
                driverCRUDService.createDriver(driver);
                return "redirect:/driver/show/all";
            } catch (Exception e) {
                return "error";
            }
        }
    }
    @GetMapping("/update")
    public String updateDriver(Model model, @RequestParam(required = false) int id) {

        try {
            model.addAttribute("title", "Update Driver");
            Driver driver = driverCRUDService.getDriverById(id);
            model.addAttribute("driver", driver);
            model.addAttribute("id", id); // Pass the ID to the model
            return "driver-update-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


    @PostMapping("/update")
    public String updateDriver(@RequestParam int id, @Valid Driver driver, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "driver-update-page";
        } else {
            try {
                driverCRUDService.updateDriverById(id, driver);
                return "redirect:/driver/show/all";
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                return "error";
            }
        }
    }

}




