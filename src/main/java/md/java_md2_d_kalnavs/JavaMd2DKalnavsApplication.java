package md.java_md2_d_kalnavs;

import md.java_md2_d_kalnavs.Models.*;
import md.java_md2_d_kalnavs.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaMd2DKalnavsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMd2DKalnavsApplication.class, args);
    }

    @Bean
    public CommandLineRunner testDatabase(IAddressRepo addressRepo, IDriverRepo driverRepo,
                                          IParcelRepo parcelRepo, IPersonRepo personRepo, ICustomerAsPersonRepo customerRepo, ICustomerAsCompany company) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Person p1 = new Person("Janis", "Berzins", "130202-20821");
                Person p2 = new Person("Juris", "Berzins", "210222-20321");
                personRepo.save(p1);
                personRepo.save(p2);

                Address a1 = new Address(City.Liepaja, 10, "Liela iela");
                Address a2 = new Address(City.Riga, 21, "Maza iela");
                addressRepo.save(a1);
                addressRepo.save(a2);

                CustomerAsCompany c1 = new CustomerAsCompany(a1, "+24423747", "Kalmars", "LV20394839214");
                company.save(c1);

                CustomerAsPerson c2 = new CustomerAsPerson(p1,a1,"24423747");
                CustomerAsPerson c3 = new CustomerAsPerson(p2,a1,"24423747");
                customerRepo.save(c2);
                customerRepo.save(c3);


                Driver d1 = new Driver("Rudolfs", "Kalmars", "130301-20821", "AT789221", 8.3f);
                Driver d2 = new Driver("Daniels", "Kalnas", "210210-21221", "AT832221", 1.2f);

                driverRepo.save(d1);
                driverRepo.save(d2);
            }
        };
    }


}
