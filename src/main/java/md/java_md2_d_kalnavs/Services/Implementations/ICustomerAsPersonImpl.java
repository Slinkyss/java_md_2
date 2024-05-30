package md.java_md2_d_kalnavs.Services.Implementations;

import lombok.Setter;
import md.java_md2_d_kalnavs.Models.*;
import md.java_md2_d_kalnavs.Services.ICustomerService;
import md.java_md2_d_kalnavs.repo.ICustomerAsCompany;
import md.java_md2_d_kalnavs.repo.ICustomerAsPersonRepo;
import md.java_md2_d_kalnavs.repo.IPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICustomerAsPersonImpl implements ICustomerService {

    @Autowired
    private ICustomerAsCompany customerAsCompanyRepp;

    @Autowired
    private ICustomerAsPersonRepo customerAsPersonRepo;


    @Autowired
    IPersonRepo personRepo;




    @Override
    public void InsertNewCustomerAsPerson(CustomerAsPerson customer) throws Exception {
        if (customer == null || customer.getPerson() == null) {
            throw new Exception("Input is wrong");
        }

        Person person = customer.getPerson();
        if (person == null) {
            throw new Exception("Person details are missing");
        }

        personRepo.save(person);

        CustomerAsPerson customerAsPerson = new CustomerAsPerson();
        customerAsPersonRepo.save(customer);
    }

    @Override
    public void addAddressToCustomerById(Address address, int Id) throws Exception {

        if(address == null || Id <= 0){
            throw new Exception("Input is wrong");
        }

        if(customerAsPersonRepo.existsById(Id)){
            CustomerAsPerson customer = customerAsPersonRepo.findById(Id).get();

            customer.setAddress(address);
            customerAsPersonRepo.save(customer);
        }




    }

    @Override
    public void InsertNewCompanyAsCustomer(CustomerAsCompany customer) throws Exception {
        if(customer == null){
            throw new Exception("Input is wrong");
        }

        CustomerAsCompany newC = new CustomerAsCompany(customer.getPhoneNo(), customer.getTitle(), customer.getGetCompanyRegNo());
        customerAsCompanyRepp.save(newC);

    }
}
