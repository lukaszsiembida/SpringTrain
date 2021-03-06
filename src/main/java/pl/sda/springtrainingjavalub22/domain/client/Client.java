package pl.sda.springtrainingjavalub22.domain.client;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    private Long id;
    @NotBlank(message = "Name should be not blank")
    private String name;
    @NotBlank(message = "Surname should be not blank")
    private String surname;

    private String driverLicenceNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate accountCreationDate;

}
