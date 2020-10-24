package pl.sda.springtrainingjavalub22.api.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NewCar {
    @Setter
    private Long id;
    @NotEmpty
    private String manufacturer;
    @NotNull
    private String model;
    @NotNull
    @Positive
    private Integer yearOfProduction;
    @NotEmpty
    private String vin;
}
