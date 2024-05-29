package md.java_md2_d_kalnavs.Services.Implementations;

import md.java_md2_d_kalnavs.Models.*;
import md.java_md2_d_kalnavs.Services.IParcelService;
import md.java_md2_d_kalnavs.repo.ICustomerAsCompany;
import md.java_md2_d_kalnavs.repo.ICustomerAsPersonRepo;
import md.java_md2_d_kalnavs.repo.IDriverRepo;
import md.java_md2_d_kalnavs.repo.IParcelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class IParcelServiceImpl implements IParcelService {

    @Autowired
    private IParcelRepo parcelRepo;

    @Autowired
    private ICustomerAsCompany companyRepo;

    @Autowired
    private ICustomerAsPersonRepo customerRepo;

    @Autowired
    private IDriverRepo driverRepo;

    @Override
    public ArrayList<Parcel> selectAllParcelsByCustomersId(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Id input is wrong");
        }


        return parcelRepo.findByAbstractCustomer_cID(id);
    }


    @Override
    public ArrayList<Parcel> selectAllParcelsByDriversId(int id) throws Exception {
        if(id <= 0){
            throw new Exception("Id input is wrong");
        }

        ArrayList<Parcel> parcels = new ArrayList<>();

        if(driverRepo.existsById(id)){
            Driver driver = driverRepo.findById(id).get();
            parcels.addAll(driver.getParcels());
            if(parcels.isEmpty()){
                throw new Exception("there is no parcel with this id");
            }
        }else{
            throw new Exception("there is no driver with this id ");
        }



        return parcels;
    }

    @Override
    public ArrayList<Parcel> selectAllParcelsPriceLessThan(int price) throws Exception {

        if(price <= 0){
            throw new Exception("Price input is wrong");
        }



         return parcelRepo.findByPriceLessThan(price);


    }

    @Override
    public ArrayList<Parcel> selectAllParcelsByCity(City city) throws Exception {
        if(city == null){
            throw new Exception("City input is wrong");
        }
        return parcelRepo.findByAbstractCustomer_Address_City(city);
    }

    @Override
    public void changeParcelDriverByParcelIdAndDriverId(int parcelId, int DriverId) throws Exception {
        if(parcelId < 0 || DriverId < 0){
            throw new Exception("Id input is wrong");
        }
        if(!parcelRepo.existsById(parcelId)){
            throw new Exception("Parcel with this id does not exist");
        }
        if(!driverRepo.existsById(DriverId)){
            throw new Exception("Driver with this id does not exist");
        }

        Parcel parcel = parcelRepo.findById(parcelId).get();
        parcel.setDriver(driverRepo.findById(DriverId).get());
        parcelRepo.save(parcel);

    }

    @Override
    public float calculateIncomeOfParcelByCustomerId(int CustomersId) throws Exception {
        if(CustomersId < 0){
            throw new Exception("Id input is wrong");
        }

        ArrayList<Parcel> parcels = selectAllParcelsByCustomersId(CustomersId);

        float sum = 0;

        for (Parcel parcel : parcels) {
            sum = sum + parcel.getPrice();
        }
        return sum;
    }

    @Override
    public int calculateHowManyParcelsNeedToDeliverToday() throws Exception {

        ArrayList<Parcel> parcels = (ArrayList<Parcel>) parcelRepo.findAll();
        int sum = 0;

        for (Parcel parcel : parcels) {
            if(parcel.getPlannedDelivery().getDayOfYear() == LocalDateTime.now().getDayOfYear()){
                sum ++;
            }
        }
        return sum;
    }

    @Override
    public void insertParcel(ParcelSize size, Boolean isFragile, int DriverId, int CustomersId) throws Exception {
        if(size == null  || DriverId < 0 || CustomersId < 0){
            throw new Exception("Input is wrong");
        }

        Driver driver = driverRepo.findById(DriverId).get();
        AbstractCustomer customer = companyRepo.findById(CustomersId).get();

        Parcel parcel = new Parcel(size,isFragile,driver,customer);

        parcelRepo.save(parcel);
    }
    }




