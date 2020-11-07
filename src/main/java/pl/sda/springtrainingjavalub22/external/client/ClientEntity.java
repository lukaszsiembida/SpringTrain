package pl.sda.springtrainingjavalub22.external.client;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name should be not blank")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Surname should be not blank")
    private String surname;

    @Column(nullable = false, unique = true, length = 20)
    private String driverLicenceNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate accountCreationDate;

}
