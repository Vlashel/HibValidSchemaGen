package com.vlashel.util.JacksonJsonExtensions.validation;

import com.fasterxml.jackson.databind.BeanProperty;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaDescription;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaTitle;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Created by vshel on 9/26/2014.
 */
public class AnnotationConstraintResolver implements ValidationConstraintResolver {

    @Override
    public String getDescription(BeanProperty prop) {

        SchemaDescription schemaDescriptionAnnotation = prop.getAnnotation(SchemaDescription.class);

        return schemaDescriptionAnnotation != null ? schemaDescriptionAnnotation.value() : null;
    }

    @Override
    public String getTitle(BeanProperty prop) {

        SchemaTitle schemaTitleAnnotation = prop.getAnnotation(SchemaTitle.class);
        return schemaTitleAnnotation != null ? schemaTitleAnnotation.value() : null;
    }

    @Override
    public String getPattern(BeanProperty prop) {

        Email emailAnnotation = prop.getAnnotation(Email.class);
        if (emailAnnotation != null) {
            return emailAnnotation.regexp();
        }

        Pattern patternAnnotation = prop.getAnnotation(Pattern.class);

        return patternAnnotation != null ? patternAnnotation.regexp() : null;
    }

    @Override
    public Boolean isRequired(BeanProperty prop) {

        NotNull notNullAnnotation = prop.getAnnotation(NotNull.class);

        return notNullAnnotation != null ? true : null;
    }

    private Size getSizeAnnotation(BeanProperty prop) {
        return prop.getAnnotation(Size.class);
    }

    private Integer getMaxSize(BeanProperty prop) {
        Size sizeAnnotation = getSizeAnnotation(prop);
        return sizeAnnotation != null && sizeAnnotation.max() != Integer.MAX_VALUE ? sizeAnnotation.max() : null;
    }

    private Integer getMinSize(BeanProperty prop) {
        Size sizeAnnotation = getSizeAnnotation(prop);
        return sizeAnnotation != null && sizeAnnotation.min() != 0 ? sizeAnnotation.min() : null;
    }

    @Override
    public Integer getArrayMaxItems(BeanProperty prop) {
        return getMaxSize(prop);
    }

    @Override
    public Integer getArrayMinItems(BeanProperty prop) {
        return getMinSize(prop);
    }

    @Override
    public Double getNumberMaximum(BeanProperty prop) {
        Max maxAnnotation = prop.getAnnotation(Max.class);
        if (maxAnnotation != null) {
            return (double) maxAnnotation.value();
        }
        DecimalMax decimalMaxAnnotation = prop.getAnnotation(DecimalMax.class);
        return decimalMaxAnnotation != null ? new BigDecimal(decimalMaxAnnotation.value()).doubleValue() : null;
    }

    @Override
    public Double getNumberMinimum(BeanProperty prop) {
        Min minAnnotation = prop.getAnnotation(Min.class);
        if (minAnnotation != null) {
            return (double) minAnnotation.value();
        }
        DecimalMin decimalMinAnnotation = prop.getAnnotation(DecimalMin.class);
        return decimalMinAnnotation != null ? new BigDecimal(decimalMinAnnotation.value()).doubleValue() : null;
    }

    @Override
    public Integer getStringMaxLength(BeanProperty prop) {
        return getMaxSize(prop);
    }

    @Override
    public Integer getStringMinLength(BeanProperty prop) {
        return getMinSize(prop);
    }
}
