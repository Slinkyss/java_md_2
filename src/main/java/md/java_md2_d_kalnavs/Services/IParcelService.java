package md.java_md2_d_kalnavs.Services;

import md.java_md2_d_kalnavs.Models.City;
import md.java_md2_d_kalnavs.Models.Driver;
import md.java_md2_d_kalnavs.Models.Parcel;
import md.java_md2_d_kalnavs.Models.ParcelSize;

import java.util.ArrayList;
import java.util.List;

public interface IParcelService {

    ArrayList<Parcel> selectAllParcelsByCustomersId(int id) throws Exception; ;
    ArrayList<Parcel> selectAllParcelsByDriversId(int id) throws Exception;
    ArrayList<Parcel> selectAllParcelsPriceLessThan(int price) throws Exception;
    ArrayList<Parcel> selectAllParcelsByCity(City city) throws Exception;
    void changeParcelDriverByParcelIdAndDriverId(int parcelId, int DriverId) throws Exception;
    float calculateIncomeOfParcelByCustomerId(int CustomersId) throws Exception;
    int calculateHowManyParcelsNeedToDeliverToday() throws Exception;
    void insertParcel(ParcelSize size, Boolean isFragile, int DriverId, int CustomersId) throws Exception;

}
