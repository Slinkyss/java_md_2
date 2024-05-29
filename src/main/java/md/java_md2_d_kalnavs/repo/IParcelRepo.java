package md.java_md2_d_kalnavs.repo;


import md.java_md2_d_kalnavs.Models.City;
import md.java_md2_d_kalnavs.Models.Parcel;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface IParcelRepo extends CrudRepository<Parcel, Integer> {
    ArrayList<Parcel> findByPriceLessThan(float price);

    List<Parcel> findByDriverId(int id);

    ArrayList<Parcel> findByAbstractCustomer_cID(int customerId);

    ArrayList<Parcel> findByAbstractCustomer_Address_City(City city);
}
