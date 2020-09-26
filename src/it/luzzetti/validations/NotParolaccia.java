package it.luzzetti.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotParolacciaValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotParolaccia {

    String message() default "Il testo contiene delle parole proibite";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Lingua lingua() default Lingua.ITALIANO;

}
