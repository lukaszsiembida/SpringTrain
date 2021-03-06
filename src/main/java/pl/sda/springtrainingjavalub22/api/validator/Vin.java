package pl.sda.springtrainingjavalub22.api.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)  // w jakim miejscu ma się pojawić adnotacja
@Retention(value = RetentionPolicy.RUNTIME) // w jakim momencie adnotacja ma dać efekt( być sparsowana)
@Constraint(validatedBy = VinValidator.class)
public @interface Vin {
    String message() default "Vin should not be empty and should have 10 signs"; // info o błędzie gdy walidacja nie przejdzie
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {}; //dane jakie dane będą walidowane
}
