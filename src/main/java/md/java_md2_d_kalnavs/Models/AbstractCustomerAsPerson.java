package md.java_md2_d_kalnavs.Models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractCustomerAsPerson extends AbstractCustomer {

    @OneToOne
    @JoinColumn(name = "Idp")
    protected Person person;

    public AbstractCustomerAsPerson(Address address, String phoneNo, Person person) {
        super(address, phoneNo);
        setPerson(person);
    }


}