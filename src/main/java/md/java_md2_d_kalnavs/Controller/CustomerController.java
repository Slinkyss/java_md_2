package md.java_md2_d_kalnavs.Controller;

import jakarta.validation.Valid;
import md.java_md2_d_kalnavs.Models.Address;
import md.java_md2_d_kalnavs.Models.CustomerAsPerson;
import md.java_md2_d_kalnavs.Models.Driver;
import md.java_md2_d_kalnavs.Models.Person;
import md.java_md2_d_kalnavs.Services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    ICustomerService customerService;



    @GetMapping("/add")
    public String showCreateCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerAsPerson(new Person(), new Address(), ""));
        return "customer-add-page";
    }

    @PostMapping("/add")
    public String createCustomer(@Valid @ModelAttribute("customer") CustomerAsPerson customer, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            return "customer-add-page";
        }
        customerService.InsertNewCustomerAsPerson(customer);
        return "redirect:/customers";
    }
}
