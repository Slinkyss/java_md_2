package md.java_md2_d_kalnavs;

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
    public CommandLineRunner TestModelLayer(IAddressRepo addressRepo, ICustomerAsCompany customerAsCompanyRepo,
                                            ICustomerAsPersonRepo customerAsPersonRepo, IDriverRepo driverRepo,
                                            IParcelRepo parcelRepo, IPersonRepo personRepo) {

        return CommanderLineRunner -> new Runnable() {
            public void run() {

            }

        };



    }



}
