package by.gstu.bot.learning.web.validator;

import by.gstu.bot.learning.domain.Place;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PlaceFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (Place.class.equals(target.getClass())) {
            Place place = (Place) target;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "form.notempty.place.name");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "form.notempty.place.type");

//            if (place.getManufacturer().equalsIgnoreCase("none")) {
//                errors.rejectValue("manufacturer", "NotEmpty.placeForm.manufacturer");
//            }
        }
    }
}
