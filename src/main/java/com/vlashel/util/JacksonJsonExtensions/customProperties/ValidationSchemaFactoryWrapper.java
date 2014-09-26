package com.vlashel.util.JacksonJsonExtensions.customProperties;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.ObjectVisitor;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.VisitorContext;
import com.fasterxml.jackson.module.jsonSchema.factories.WrapperFactory;
import com.fasterxml.jackson.module.jsonSchema.types.*;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaDescription;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaTitle;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaVersion;
import com.vlashel.util.JacksonJsonExtensions.factories.ObjectVisitorDecorator;
import com.vlashel.util.JacksonJsonExtensions.validation.AnnotationConstraintResolver;
import com.vlashel.util.JacksonJsonExtensions.validation.ValidationConstraintResolver;

/**
 * Created by vshel on 9/26/2014.
 */
public class ValidationSchemaFactoryWrapper extends SchemaFactoryWrapper {
    private ValidationConstraintResolver constraintResolver;

    private static class ValidationSchemaFactoryWrapperFactory extends WrapperFactory {
        @Override
        public SchemaFactoryWrapper getWrapper(SerializerProvider p) {
            SchemaFactoryWrapper wrapper = new ValidationSchemaFactoryWrapper();
            wrapper.setProvider(p);
            return wrapper;
        }

        @Override
        public SchemaFactoryWrapper getWrapper(SerializerProvider p, VisitorContext rvc) {
            SchemaFactoryWrapper wrapper = new ValidationSchemaFactoryWrapper();
            wrapper.setProvider(p);
            wrapper.setVisitorContext(rvc);
            return wrapper;
        }
    }

    public ValidationSchemaFactoryWrapper() {
        this(new AnnotationConstraintResolver());
    }

    public ValidationSchemaFactoryWrapper(ValidationConstraintResolver constraintResolver) {
        super(new ValidationSchemaFactoryWrapperFactory());
        this.constraintResolver = constraintResolver;
    }

    @Override
    public JsonObjectFormatVisitor expectObjectFormat(JavaType convertedType) {

        ObjectVisitorDecorator objectVisitorDecorator = new ObjectVisitorDecorator((ObjectVisitor) super.expectObjectFormat(convertedType)) {
            private JsonSchema getPropertySchema(BeanProperty writer) {
                return ((ObjectSchema) getSchema()).getProperties().get(writer.getName());
            }


            @Override
            public void optionalProperty(BeanProperty writer) throws JsonMappingException {
                super.optionalProperty(writer);
                addValidationConstraints(getPropertySchema(writer), writer);
            }

            @Override
            public void property(BeanProperty writer) throws JsonMappingException {
                super.property(writer);
                addValidationConstraints(getPropertySchema(writer), writer);
            }
        };

        addClassLevelAnnotations(objectVisitorDecorator.getSchema(), convertedType);


        return objectVisitorDecorator;
    }

    private JsonSchema addValidationConstraints(JsonSchema propertySchema, BeanProperty prop) {


        SimpleTypeSchema simpleTypeSchema = propertySchema.asSimpleTypeSchema();

        simpleTypeSchema.setTitle(constraintResolver.getTitle(prop));
        simpleTypeSchema.setDescription(constraintResolver.getDescription(prop));


        if (propertySchema.isArraySchema()) {
            ArraySchema arraySchema = propertySchema.asArraySchema();
            arraySchema.setMaxItems(constraintResolver.getArrayMaxItems(prop));
            arraySchema.setMinItems(constraintResolver.getArrayMinItems(prop));
            arraySchema.setRequired(constraintResolver.isRequired(prop));
        } else if (propertySchema.isNumberSchema()) {
            NumberSchema numberSchema = propertySchema.asNumberSchema();
            numberSchema.setMaximum(constraintResolver.getNumberMaximum(prop));
            numberSchema.setMinimum(constraintResolver.getNumberMinimum(prop));
            numberSchema.setRequired(constraintResolver.isRequired(prop));
        } else if (propertySchema.isStringSchema()) {
            StringSchema stringSchema = propertySchema.asStringSchema();
            stringSchema.setMaxLength(constraintResolver.getStringMaxLength(prop));
            stringSchema.setMinLength(constraintResolver.getStringMinLength(prop));
            stringSchema.setRequired(constraintResolver.isRequired(prop));
            stringSchema.setPattern(constraintResolver.getPattern(prop));

        }
        return propertySchema;
    }


    private void addClassLevelAnnotations(JsonSchema schema, JavaType type) {
        if (!schema.isSimpleTypeSchema()) {
            throw new RuntimeException("given non simple type schema: " + schema.getType());
        }

        Class clazz = type.getRawClass();

        SchemaTitle schemaTitleAnnotation = (SchemaTitle) clazz.getAnnotation(SchemaTitle.class);
        SchemaDescription schemaDescriptionAnnotation = (SchemaDescription) clazz.getAnnotation(SchemaDescription.class);
        SchemaVersion schemaVersionAnnotation = (SchemaVersion) clazz.getAnnotation(SchemaVersion.class);


        if (schemaTitleAnnotation != null) {
            schema.asSimpleTypeSchema().setTitle(schemaTitleAnnotation.value());
        }

        if (schemaDescriptionAnnotation != null) {
            schema.asSimpleTypeSchema().setDescription(schemaDescriptionAnnotation.value());
        }

        if (schemaVersionAnnotation != null) {
            schema.asSimpleTypeSchema().set$schema(schemaVersionAnnotation.value());
        }
    }
}
