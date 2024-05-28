package md.java_md2_d_kalnavs.Services.Implementations;

import md.java_md2_d_kalnavs.Models.Driver;
import md.java_md2_d_kalnavs.Models.Parcel;
import md.java_md2_d_kalnavs.Services.IDriverCRUDService;
import md.java_md2_d_kalnavs.repo.IDriverRepo;
import md.java_md2_d_kalnavs.repo.IParcelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class IDriverCRUDServiceImpl implements IDriverCRUDService {

     @Autowired
     private IDriverRepo driverRepo;
     @Autowired
     private IParcelRepo parcelRepo;

    @Override
    public ArrayList<Driver> getAllDrivers() throws Exception {

        ArrayList<Driver> Drivers = new ArrayList<>();
        Drivers.addAll((Collection<? extends Driver>) driverRepo.findAll());
        if(Drivers.isEmpty()){
            throw new Exception("There are no drivers");
        }
        return Drivers;
    }

    @Override
    public Driver getDriverById(int id) throws Exception {
        if(id <= 0){
            throw new Exception("Drivers id input is wrong");
        }

        if(driverRepo.findById(id).isPresent()){
            Driver driver = driverRepo.findById(id).get();
            return driver;
        } else {
            throw new Exception("There is no driver with this id");
        }


    }

    @Override
    public void createDriver(Driver driver) throws Exception {
        if(driver == null){
            throw new Exception("Something wrong with input");
        }
        Driver newDriver = new Driver(driver.getName(),driver.getSurname(), driver.getPersonCode(), driver.getLicenseNo(), driver.getExperienceInYears());
        driverRepo.save(newDriver);

    }


    @Override
    public void updateDriverById(int id) throws Exception {
        if(id <= 0){
            throw new Exception("Drivers id input is wrong");
        }
        Driver updatedDriver = driverRepo.findById(id).get();
        updatedDriver.setName(updatedDriver.getName());
        updatedDriver.setSurname(updatedDriver.getSurname());
        updatedDriver.setPersonCode(updatedDriver.getPersonCode());
        updatedDriver.setLicenseNo(updatedDriver.getLicenseNo());
        updatedDriver.setExperienceInYears(updatedDriver.getExperienceInYears());

        driverRepo.save(updatedDriver);
    }


    public void deleteDriver(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("Driver ID input is wrong");
        }
        if (driverRepo.findById(id).isPresent()) {
            // Check if there are any associated parcels
            List<Parcel> parcels = parcelRepo.findByDriverId(id);
            if (!parcels.isEmpty()) {
                // Delete associated parcels
                parcelRepo.deleteAll(parcels);
            }
            // Now delete the driver
            driverRepo.deleteById(id);
        } else {
            throw new Exception("There is no driver with this ID");
        }
    }
}
