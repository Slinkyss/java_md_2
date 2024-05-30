package md.java_md2_d_kalnavs.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.converter.json.GsonBuilderUtils;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Customer_As_Person")
@Entity
public class CustomerAsPerson extends AbstractCustomerAsPerson {

    protected Person person;




    public void setCustomerCode() {


        this.customerCode = String.valueOf(super.getCID()) + "_" + "person" + "_" + person.getPersonCode();

    }
    public CustomerAsPerson(Person person, String phoneNo) {
        super(null,phoneNo,person);
        setPerson(person);
        setCustomerCode();
    }

    public CustomerAsPerson(Person person, Address address, String phone) {

        super(address,phone,person);
        setCustomerCode();

    }

}
