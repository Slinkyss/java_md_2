package md.java_md2_d_kalnavs.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Driver_table")
@Entity
public class Driver extends Person{


    @NotNull
    @Size(min = 8, max = 8)
    @Pattern(regexp = "(^[A-Z]{2}\\d+)")
    private String licenseNo;


    @NotNull
    @Min(0)
    private float ExperienceInYears;

    @OneToMany(mappedBy = "driver")
    private ArrayList<Parcel> parcels;


    public Driver(String name, String surname, String personCode, String licenseNo, float experienceInYears) {
        super(name, surname, personCode);
        setLicenseNo(licenseNo);
        setExperienceInYears(experienceInYears);
    }

}
