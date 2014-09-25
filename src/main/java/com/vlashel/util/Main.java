package com.vlashel.util;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.customProperties.HyperSchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.vlashel.dto.UserDto;

import java.io.File;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 25.09.2014
 */
public class Main {
    public static void main(String[] args) throws Exception{

        ObjectMapper m = new ObjectMapper();
        m.setAnnotationIntrospector(new CustomAnnotationIntrospector());
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        m.acceptJsonFormatVisitor(m.constructType(UserDto.class), visitor);
        JsonSchema jsonSchema = visitor.finalSchema();
        m.writeValue(new File("schema.json"), jsonSchema);

    }
}
