package pl.sda.springtrainingjavalub22.api.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE) // adnotacja nad klasą
@Constraint(validatedBy = ManufacturerAndModelValidator.class)
public @interface ManufacturerAndModel {
    String message() default "Manufacturer and model aren't coexisting"; // info o błędzie gdy walidacja nie przejdzie
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {}; //dane jakie dane będą walidowane
}
