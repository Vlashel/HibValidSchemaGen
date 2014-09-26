package com.vlashel.util.JacksonJsonExtensions.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 26.09.2014
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SchemaVersion {
    String value() default "";
}
