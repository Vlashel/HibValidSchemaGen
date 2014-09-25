package com.vlashel.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 25.09.2014
 */
public class CustomAnnotationIntrospector extends JacksonAnnotationIntrospector {
    @Override
    public Version version() {
        return null;
    }

    @Override
    public Boolean hasRequiredMarker(AnnotatedMember m) {
        NotEmpty ann = m.getAnnotation(NotEmpty.class);
        if (ann != null) {
            return true;
        }
        return null;
    }
}
