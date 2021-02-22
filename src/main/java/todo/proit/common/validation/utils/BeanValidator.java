package todo.proit.common.validation.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author dipanjal
 * @since 11/27/2020
 */
public class BeanValidator {

    private static Validator beanValidator = null;

    public static <T,G> Set<ConstraintViolation<T>> validateBean(T model, Class<G>... groups){
        if(beanValidator == null)
            beanValidator = Validation.buildDefaultValidatorFactory().getValidator();

        if(ArrayUtils.isEmpty(groups))
            return beanValidator.validate(model);

        Set<ConstraintViolation<T>> violationsForGroup = beanValidator.validate(model, groups);
        Set<ConstraintViolation<T>> violationsCommon = beanValidator.validate(model);
        return Stream
                .concat(violationsCommon.stream(), violationsForGroup.stream())
                .collect(Collectors.toSet());
    }

    public static <T, G> List<String> validateBeanAndGetErrors(T model, Class<G>... groups){

        Set<ConstraintViolation<T>> violations = validateBean(model, groups);
        return violations
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    public static <T, G> String validateBeanAndGetMessage(T model, Class<G>... groups){
        List<String> errors = validateBeanAndGetErrors(model, groups);
        return StringUtils.join(errors, ", ");
    }
}
