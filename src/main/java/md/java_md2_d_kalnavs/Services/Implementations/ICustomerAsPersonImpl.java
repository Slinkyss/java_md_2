package md.java_md2_d_kalnavs.Services.Implementations;

import lombok.Setter;
import md.java_md2_d_kalnavs.Models.AbstractCustomer;
import md.java_md2_d_kalnavs.Models.Address;
import md.java_md2_d_kalnavs.Models.CustomerAsCompany;
import md.java_md2_d_kalnavs.Models.CustomerAsPerson;
import md.java_md2_d_kalnavs.Services.ICustomerService;
import md.java_md2_d_kalnavs.repo.ICustomerAsCompany;
import md.java_md2_d_kalnavs.repo.ICustomerAsPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICustomerAsPersonImpl implements ICustomerService {

    @Autowired
    private ICustomerAsCompany customerAsCompanyRepp;

    @Autowired
    private ICustomerAsPersonRepo customerAsPersonRepo;





    @Override
    public void InsertNewCustomerAsPerson(CustomerAsPerson customer) throws Exception {

        if(customer == null){
            throw new Exception("Input is wrong");
        }

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
        customerAsCompanyRepp.save(customer);

    }
}
