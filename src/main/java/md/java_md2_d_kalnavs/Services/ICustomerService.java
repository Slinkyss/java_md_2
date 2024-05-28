package md.java_md2_d_kalnavs.Services;

import md.java_md2_d_kalnavs.Models.Address;
import md.java_md2_d_kalnavs.Models.CustomerAsCompany;
import md.java_md2_d_kalnavs.Models.CustomerAsPerson;
import md.java_md2_d_kalnavs.Models.Person;

public interface ICustomerService {

    void InsertNewCustomerAsPerson(CustomerAsPerson customer) throws Exception;

    void addAddressToCustomerById(Address address, int Id)throws Exception;
    void InsertNewCompanyAsCustomer(CustomerAsCompany customer)throws Exception;
}
