package md.java_md2_d_kalnavs.Services;

import md.java_md2_d_kalnavs.Models.Driver;

import java.util.ArrayList;


public interface IDriverCRUDService {


    ArrayList<Driver> getAllDrivers() throws Exception;
    Driver getDriverById(int id) throws Exception;
    void createDriver(Driver driver) throws Exception;
    void updateDriverById(int id) throws Exception;
    void deleteDriver(int id) throws Exception;






}
