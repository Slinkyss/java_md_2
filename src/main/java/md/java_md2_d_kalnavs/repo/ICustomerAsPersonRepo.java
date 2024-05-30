package md.java_md2_d_kalnavs.repo;

import md.java_md2_d_kalnavs.Models.CustomerAsPerson;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ICustomerAsPersonRepo extends CrudRepository<CustomerAsPerson, Integer> {

    CustomerAsPerson findCustomerByCustomerCode(String customerCode);
}
