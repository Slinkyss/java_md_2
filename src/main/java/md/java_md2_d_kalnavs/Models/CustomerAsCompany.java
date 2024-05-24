package md.java_md2_d_kalnavs.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Customer_As_Company")
@Entity
public class CustomerAsCompany extends AbstractCustomer{


    @NotNull
    @Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")
    @Size(min = 1, max = 50)
    public String title;

    @NotNull
    public String getCompanyRegNo;


    public void setCustomerCode() {

        this.customerCode = String.valueOf(super.getCID()) + "_" + getTitle() + "_" + getCompanyRegNo;
    }


    public CustomerAsCompany(Address address, String phoneNo, String title, String companyRegNo) {
        super(address, phoneNo);
        setTitle(title);
        setGetCompanyRegNo(companyRegNo);
        setCustomerCode();
    }
}
