package com.vlashel.util.JacksonJsonExtensions.validation;

import com.fasterxml.jackson.databind.BeanProperty;

/**
 * Created by vshel on 9/26/2014.
 */
public interface ValidationConstraintResolver {
    Integer getArrayMaxItems(BeanProperty prop);

    Integer getArrayMinItems(BeanProperty prop);

    Double getNumberMaximum(BeanProperty prop);

    Double getNumberMinimum(BeanProperty prop);

    Integer getStringMaxLength(BeanProperty prop);

    Integer getStringMinLength(BeanProperty prop);

    Boolean isRequired(BeanProperty prop);

    String getPattern(BeanProperty prop);

    String getTitle(BeanProperty prop);

    String getDescription(BeanProperty prop);
}
