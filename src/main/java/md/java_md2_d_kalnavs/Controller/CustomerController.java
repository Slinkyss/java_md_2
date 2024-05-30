package md.java_md2_d_kalnavs.Controller;

import jakarta.validation.Valid;
import md.java_md2_d_kalnavs.Models.*;
import md.java_md2_d_kalnavs.Services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    ICustomerService customerService;





    @GetMapping("/add")
    public String addCustomer(Model model) {
        try {
            model.addAttribute("customer", new CustomerAsPerson());
            model.addAttribute("title", "Add customer");
            return "customer-add-page";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/add")
    public String addCustomer(@Valid  CustomerAsPerson customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Add customer");
            return "customer-add-page";
        } else {
            try {
                customerService.InsertNewCustomerAsPerson(customer);
                return "redirect:/driver/show/all";
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                return "error";
            }
        }
    }



@GetMapping("/addCompany")
public String addCompany(Model model) {
    try {
        model.addAttribute("company", new CustomerAsCompany());
        model.addAttribute("title", "Add company");
        return "company-add-page";
    } catch (Exception e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}

@PostMapping("/addCompany")
public String addCompany(@Valid  CustomerAsCompany company, BindingResult result, Model model) {
    if (result.hasErrors()) {
        model.addAttribute("title", "Add company");
        return "company-add-page";
    } else {
        try {
            customerService.InsertNewCompanyAsCustomer(company);
            return "redirect:/driver/show/all";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
}