package md.java_md2_d_kalnavs.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Address_Table")
@Entity
public class Address {

    @Id
    @Setter(value = AccessLevel.NONE)
    @Column(name = "Ida")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ida;

    @NotNull
    @Column(name = "City")
    private City city;

    @NotNull
    @Min(1)
    @Max(1000)
    @Column(name = "House_No")
    private int houseNo;

    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "[A-Z][A-Za-z0-9 -]+")
    @Column(name = "Street_house_title")
    private String streetOrHouseTitle;


    @ManyToOne
    @JoinColumn(name = "Idc")
    private AbstractCustomer abstractCustomer;


    public Address(City city, int houseNo, String streetOrHouseTitle) {
        setCity(city);
        setHouseNo(houseNo);
        setStreetOrHouseTitle(streetOrHouseTitle);
    }
}
