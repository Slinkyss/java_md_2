package md.java_md2_d_kalnavs.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Person_table")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Person {


    @Id
    @Setter(value = AccessLevel.NONE)
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")
    @Column(name = "Name")
    private String name;

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")
    @Column(name = "Surname")
    private String surname;

    @NotNull
    @Size(min = 11, max = 11)
    @Pattern(regexp = "([0-9]+[-]+[0-9]+)")
    @Column(name = "PersonCode")
    private String personCode;


    public Person(String name, String surname, String personCode) {
        setName(name);
        setSurname(surname);
        setPersonCode(personCode);
    }

}
