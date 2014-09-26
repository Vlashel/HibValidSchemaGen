package com.vlashel.dto;

import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaDescription;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaTitle;
import com.vlashel.util.JacksonJsonExtensions.annotations.SchemaVersion;

/**
 * @author Vladyslav Shelest
 * @version 1.0
 * @since 26.09.2014
 */
@SchemaTitle("dummy address title")
@SchemaDescription("dummy address description")
@SchemaVersion("http://json-schema.org/draft-03/schema#")
public class Address {
    private String dummyAddress;
    private String dummyStreet;

    public String getDummyAddress() {
        return dummyAddress;
    }

    public void setDummyAddress(String dummyAddress) {
        this.dummyAddress = dummyAddress;
    }

    public String getDummyStreet() {
        return dummyStreet;
    }

    public void setDummyStreet(String dummyStreet) {
        this.dummyStreet = dummyStreet;
    }
}
