package inc.redpill.resources;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ResourceValidator implements ConstraintValidator<AResource, Resource> {
    @Override
    public boolean isValid(Resource value, ConstraintValidatorContext context) {
        System.out.println("ResourceValidator");
        return true;
    }
}
