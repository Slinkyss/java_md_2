package md.java_md2_d_kalnavs.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Parcel_Table")
@Entity
public class Parcel {

    @Id
    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idpar")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Idpar;


    @NotNull
    @Column(name = "Is_fragile")
    private boolean isFragile;

    @NotNull
    @Column(name = "price")
    private float price;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "Idp")
    private Driver driver;
    @NotNull
    @Column(name = "size")
    private ParcelSize size;

    @NotNull
    @Column(name = "Order_Created")
    private LocalDate orderCreated = LocalDate.now();

    @NotNull
    @Column(name = "Planned_Delivery")
    private LocalDate plannedDelivery;


    public void setPrice(ParcelSize size, Boolean isFragile) {
        switch(size){
            case L:
                if(isFragile){
                    this.price = (float) (4 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (4 * 1.99); break;
            case XL:
                if(isFragile){
                    this.price = (float) (5 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (5 * 1.99); break;
            case M:
                if(isFragile){
                    this.price = (float) (3 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (3 * 1.99); break;
            case S:
                if(isFragile){
                    this.price = (float) (2 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (2 * 1.99); break;

            case X:
                if(isFragile){
                    this.price = (float) (1 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (1 * 1.99); break;

        }
    }
    public void setPlannedDelivery() {

        if(getDayNumberNew(orderCreated) == 7 ){
            this.plannedDelivery = orderCreated.plusDays(4);
        }
        if(getDayNumberNew(orderCreated) == 6 ){
            this.plannedDelivery = orderCreated.plusDays(5);
        }
        if(getDayNumberNew(orderCreated) == 5 ){
            this.plannedDelivery = orderCreated.plusDays(5);
        }
        if(getDayNumberNew(orderCreated) == 4 ){
            this.plannedDelivery = orderCreated.plusDays(6);
        }
        if(getDayNumberNew(orderCreated) == 3 ){
            this.plannedDelivery = orderCreated.plusDays(7);
        }
        if(getDayNumberNew(orderCreated) == 2 ){
            this.plannedDelivery = orderCreated.plusDays(3);
        }
        if(getDayNumberNew(orderCreated) == 1 ){
            this.plannedDelivery = orderCreated.plusDays(3);
        }

    }

    @ManyToOne
    @JoinColumn(name = "Idc")
    private AbstractCustomer abstractCustomer;

    public static int getDayNumberNew(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }

    public Parcel( ParcelSize size, boolean isFragile, Driver driver){
        setPrice(size, isFragile);
        setPlannedDelivery();
        setDriver(driver);
        setFragile(isFragile);
        setSize(size);
        setPrice(size,isFragile);
    }
}
