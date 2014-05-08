/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.dynantec.validador;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Action
 */
public class ValidCNPJImpl implements ConstraintValidator<ValidCNPJ, String> {

    @Override
    public void initialize(ValidCNPJ constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {       
        Pattern pat =  Pattern.compile("[0-9]{2}.[0-9]{3}.[0-9]{3}/[0-9]{4}-[0-9]{2}");
        Matcher m = pat.matcher(value);
        return m.matches();

    }


}
