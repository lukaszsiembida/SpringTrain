package pl.sda.springtrainingjavalub22.domain.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Client {

    private String name;
    private String surname;
    @Setter
    private String driverLicenceNumber;
    private Date accountCreationDate;

}
