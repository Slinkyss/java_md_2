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
    @Setter(value = AccessLevel.NONE)
    @Column(name = "Idc")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cID;

    @OneToOne
    @JoinColumn(name = "Ida")
    private Address address;

    @NotNull
    @Size(min = 8,max = 8)
    @Pattern(regexp = "([0-9]+)")
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
