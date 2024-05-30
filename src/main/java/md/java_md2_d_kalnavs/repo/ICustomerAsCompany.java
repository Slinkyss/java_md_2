package md.java_md2_d_kalnavs.repo;

import md.java_md2_d_kalnavs.Models.CustomerAsCompany;
import md.java_md2_d_kalnavs.Models.CustomerAsPerson;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerAsCompany extends CrudRepository<CustomerAsCompany,Integer> {

    CustomerAsCompany findCustomerAsCompaniesByCustomerCode(String customerCode);
}
