package com.vlashel.util;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.customProperties.TitleSchemaFactoryWrapper;
import com.vlashel.dto.UserDto;
import com.vlashel.util.JacksonJsonExtensions.customProperties.ValidationSchemaFactoryWrapper;

import java.io.*;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 25.09.2014
 */
public class Main {
    public static void main(String[] args) throws Exception{

        ObjectMapper m = new ObjectMapper();
        ValidationSchemaFactoryWrapper visitor = new ValidationSchemaFactoryWrapper();
       // TitleSchemaFactoryWrapper visitor = new TitleSchemaFactoryWrapper();
        m.acceptJsonFormatVisitor(m.constructType(UserDto.class), visitor);
        JsonSchema jsonSchema = visitor.finalSchema();

        m.writerWithDefaultPrettyPrinter().writeValue(new File("schema.json"), jsonSchema);

    }
}
