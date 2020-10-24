package pl.sda.springtrainingjavalub22.domain.car;

import lombok.*;
import pl.sda.springtrainingjavalub22.api.validator.InsurancePeriod;
import pl.sda.springtrainingjavalub22.api.validator.ManufacturerAndModel;
import pl.sda.springtrainingjavalub22.api.validator.Vin;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@ManufacturerAndModel
@InsurancePeriod
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Car {
    @Setter
    private Long id;
    @NotBlank(message = "Manufacturer should be not blank")
    private String manufacturer;
    @NotBlank
    private String model;
    @Positive(message = "Year of production could not be negative")
    @NotNull
    private Integer yearOfProduction;
    @Vin
    private String vin;
    private LocalDate insuredFrom;
    private LocalDate insuredTo;

}
