package md.java_md2_d_kalnavs.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class AbstractCustomer {



    @Id
    @Column(name = "Idc")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cID;

    @ManyToOne
    @JoinColumn(name = "Ida")
    private Address address;

    @NotNull
    @Size(min = 8, max = 8)
    @Column(name = "PhoneNo")
    private String phoneNo;


    @NotNull
    @Column(name = "Customer_Code")
    protected  String customerCode;


    @OneToMany(mappedBy = "abstractCustomer")
    private ArrayList<Parcel> parcels;


    public AbstractCustomer(Address address, String phoneNo) {
        setAddress(address);
        setPhoneNo(phoneNo);
    }

}
